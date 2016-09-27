package com.slemarchand.serverbanner;

import java.util.Map;

public class VariablesResolver {

	public String resolveVariables(Map<String, Object> context, String template) {

		String output = template;

		for (Map.Entry<String, Object> entry : context.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue().toString();
			
			output = output.replace("${" + key + "}", value);
			output = output.replace("$" + key, value);
		}

		return output;
	}
}
