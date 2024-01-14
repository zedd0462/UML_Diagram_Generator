package org.mql.java.umlgen.utils;

import java.lang.reflect.Modifier;

public class StringUtils {

	private StringUtils() {} //class for static methods only
	
	public static String getClassShortName(String className) {
		String[] parts = className.split("[.$]"); 
		return parts[parts.length - 1];
	}
	
	public static String getAccessModifierSymbol(int modifiers) {
		if (Modifier.isPublic(modifiers)) return "+";
		if (Modifier.isProtected(modifiers)) return "#";
		if (Modifier.isPrivate(modifiers)) return "-";
		return "~";
	}
	
	

}
