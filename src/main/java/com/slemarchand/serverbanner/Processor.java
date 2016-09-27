package com.slemarchand.serverbanner;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.taglib.util.OutputData;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.slemarchand.serverbanner.model.Configuration;
import com.slemarchand.serverbanner.util.ResourceUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class Processor {

	private final static Log LOG = LogFactoryUtil.getLog(Processor.class);

	protected final String template = ResourceUtil
			.getResourceAsString("/template.html");

	protected ConfigurationFactory configurationFactory = new ConfigurationFactory();

	protected VariablesResolver variablesResolver = new VariablesResolver();

	public void process(HttpServletRequest request) throws PortalException,
			SystemException {
	
		Configuration configuration = configurationFactory.getConfiguration();

		if (configuration.isEnabled()
				&& Validator.isNotNull(configuration.getMessage())) {

			ThemeDisplay themeDisplay = (ThemeDisplay) request
					.getAttribute(WebKeys.THEME_DISPLAY);

			List<String> themeIds = configuration.getThemeIds();

			List<String> roleNames = configuration.getRoleNames();

			if (themeDisplay != null 
					&& (themeIds.isEmpty() || isOneOfThemes(themeDisplay, themeIds))
					&& (roleNames.isEmpty() || hasOneOfRoles(themeDisplay, roleNames))) {

				String html = buildHtml(themeDisplay, request, configuration);

				insertHtmlAtTopOfBody(request, html);
			}
		}
	}

	protected String buildHtml(ThemeDisplay themeDisplay,
			HttpServletRequest request, Configuration configuration) {

		Map<String, Object> context = new HashMap<String, Object>();

		context.put("jvmRoute", getJvmRoute(request));
		context.put("serverName", request.getServerName());
		context.put("serverPort", request.getServerPort());

		User user = themeDisplay.getUser();

		context.put("emailAddress", user.getEmailAddress());
		context.put("screenName", user.getScreenName());

		String message = variablesResolver.resolveVariables(context,
				configuration.getMessage());

		context.clear();
		context.put("message", message);
		context.put("color", configuration.getColor());
		context.put("backgroundColor", configuration.getBackgroundColor());
		context.put("size", configuration.getSize());

		String html = variablesResolver.resolveVariables(context, template);

		return html;
	}

	private String getJvmRoute(HttpServletRequest request) {

		String jvmRoute;

		String sessionId = request.getSession(true).getId();

		int dotIndex = sessionId.indexOf('.');

		if (dotIndex != -1) {
			jvmRoute = sessionId.substring(dotIndex + 1);
		} else {
			jvmRoute = StringPool.BLANK;
		}

		return jvmRoute;
	}

	protected void insertHtmlAtTopOfBody(HttpServletRequest request, String html) {

		OutputData outputData = getOutputData(request);

		String outputKey = this.getClass().getName();

		outputData.addData(outputKey, WebKeys.PAGE_BODY_TOP, new StringBundler(
				html));
	}

	private boolean isOneOfThemes(ThemeDisplay themeDisplay,
			List<String> themeIds) {

		boolean result = themeIds.contains(themeDisplay.getThemeId());

		if (!result && LOG.isDebugEnabled()) {
			LOG.debug("Current theme is none of these theme : "
					+ toString(themeIds));
		}

		return result;
	}

	private boolean hasOneOfRoles(ThemeDisplay themeDisplay,
			List<String> roleNames) throws PortalException, SystemException {

		boolean result = false;

		PermissionChecker permissionChecker = themeDisplay
				.getPermissionChecker();

		if (permissionChecker != null) {

			long userId = themeDisplay.getUserId();

			long groupId = themeDisplay.getSiteGroupId();

			long[] currentUserRolesIds = permissionChecker.getRoleIds(userId,
					groupId);

			Set<String> currentUserRoleNames = new HashSet<String>(
					currentUserRolesIds.length);

			for (long roleId : currentUserRolesIds) {
				Role role = RoleLocalServiceUtil.getRole(roleId);
				currentUserRoleNames.add(role.getName());
			}

			currentUserRoleNames.retainAll(roleNames);

			result = currentUserRoleNames.size() > 0;

			if (!result && LOG.isDebugEnabled()) {
				LOG.debug("Current user don't have one of these roles: "
						+ toString(roleNames));
			}

		} else {
			LOG.debug("themeDisplay.getPermissionChecker() is null");
		}

		return result;
	}

	protected OutputData getOutputData(ServletRequest servletRequest) {
		OutputData outputData = (OutputData) servletRequest
				.getAttribute(WebKeys.OUTPUT_DATA);

		if (outputData == null) {
			outputData = new OutputData();

			servletRequest.setAttribute(WebKeys.OUTPUT_DATA, outputData);
		}

		return outputData;
	}

	protected String toString(List<String> set) {
		return ListUtil.toString(set, StringPool.BLANK);
	}
}
