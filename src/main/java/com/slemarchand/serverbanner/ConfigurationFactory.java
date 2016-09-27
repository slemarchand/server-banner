package com.slemarchand.serverbanner;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.slemarchand.serverbanner.model.Configuration;
import com.slemarchand.serverbanner.util.PropsValues;

import java.util.Arrays;
import java.util.List;

public class ConfigurationFactory {
	
	private final static Log LOG = LogFactoryUtil.getLog(Processor.class);
	
	private final Configuration configuration = buildConfiguration();

	public Configuration getConfiguration() {
		return configuration;
	}
	
	protected Configuration buildConfiguration() {

		Configuration configuration = new Configuration();

		configuration.setEnabled(PropsValues.ENABLED);
		configuration.setColor(PropsValues.COLOR);
		configuration.setBackgroundColor(PropsValues.BACKGROUND_COLOR);
		configuration.setSize(PropsValues.SIZE);
		configuration.setMessage(PropsValues.MESSAGE);
		configuration.setRoleNames(toStringList(PropsValues.RESTRICTED_ROLES));
		configuration.setThemeIds(toStringList(PropsValues.RESTRICTED_THEME_IDS));

		if(LOG.isDebugEnabled()) {
			LOG.debug("Server Notification is disabled");
		}
		
		return configuration;
	}

	@SuppressWarnings("unchecked")
	protected List<String> toStringList(String[] array) {

		List<?> list = Arrays.asList(array);

		return (List<String>) list;
	}
}
