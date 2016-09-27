package com.slemarchand.serverbanner.hook.events;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.slemarchand.serverbanner.Processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServicePreAction extends Action {

	private final static Log LOG = LogFactoryUtil.getLog(Processor.class);
	
	protected Processor processor = new Processor();

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {
		
			try {
		
				processor.process(request);
			
			} catch (PortalException e) {
				LOG.error(e);
			} catch (SystemException e) {
				LOG.error(e);
			}
	}
}