package com.slemarchand.serverbanner.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.slemarchand.serverbanner.Processor;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostNameUtil {
	
	private final static Log LOG = LogFactoryUtil.getLog(Processor.class);
	
	public static String getHostName() {
	
		String result = StringPool.BLANK;	
		
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
					
			result = ip.getHostName();
			
		} catch (UnknownHostException e) {
			LOG.error(e);
		}

		return result;
	}
}
