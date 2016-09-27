package com.slemarchand.serverbanner.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ResourceUtil {

	public static String getResourceAsString(String resource) {
		
		Scanner scanner = new Scanner(
						ResourceUtil.class.getResourceAsStream(resource),
						"UTF-8"
						).useDelimiter("\\A");
		
		String resourceAsString = scanner.next();	
	
		scanner.close();
	
		return resourceAsString;
	}

}
