package com.slemarchand.serverbanner.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class PropsValues {

	public final static boolean ENABLED = GetterUtil.getBoolean(PropsUtil.get(PropsKeys.ENABLED), true);
	
	public final static String[] RESTRICTED_THEME_IDS = PropsUtil.getArray(PropsKeys.RESTRICTED_THEME_IDS);
	
	public final static String[] RESTRICTED_ROLES = PropsUtil.getArray(PropsKeys.RESTRICTED_ROLES);
	
	public final static String MESSAGE = GetterUtil.getString(PropsUtil.get(PropsKeys.MESSAGE), HostNameUtil.getHostName());
	
	public final static String COLOR = GetterUtil.getString(PropsUtil.get(PropsKeys.COLOR), "#fff");
	
	public final static String BACKGROUND_COLOR = GetterUtil.getString(PropsUtil.get(PropsKeys.BACKGROUND_COLOR), "#335C7D");
	
	public final static String SIZE = GetterUtil.getString(PropsUtil.get(PropsKeys.SIZE), "medium");
}
