package com.slemarchand.serverbanner;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class VariablesResolverTest {
	
	@Test
	public void testResolveVariables() {
		
		VariablesResolver resolver = new VariablesResolver();
		
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("var1", "v1");
		context.put("var2", "v2");
		
		String template = "$var1/${var2}";
	
		String output = resolver.resolveVariables(context, template);
		
		Assert.assertEquals("v1/v2", output);
	}
}
