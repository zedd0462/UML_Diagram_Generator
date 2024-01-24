package org.mql.java.umlgen.utils;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class StringUtils {
	private static final Map<String, String> PRIMITIVE_TYPE_MAP = new HashMap<>();

    static {
        PRIMITIVE_TYPE_MAP.put("B", "byte");
        PRIMITIVE_TYPE_MAP.put("C", "char");
        PRIMITIVE_TYPE_MAP.put("D", "double");
        PRIMITIVE_TYPE_MAP.put("F", "float");
        PRIMITIVE_TYPE_MAP.put("I", "int");
        PRIMITIVE_TYPE_MAP.put("J", "long");
        PRIMITIVE_TYPE_MAP.put("S", "short");
        PRIMITIVE_TYPE_MAP.put("Z", "boolean");
    }

	private StringUtils() {}
	
	public static String getClassShortName(String className) {
		if(className.startsWith("[L")) {
			return convertArrayNonPrimitive(className);
		}
		if(className.contains("[")) {
			return convertArrayPrimitive(className);
		}
		String[] parts = className.split("[.$]"); 
		return parts[parts.length - 1];
	}

    private static String convertArrayPrimitive(String arrayType) {
        int dimensions = arrayType.lastIndexOf('[') + 1;
        String componentType = arrayType.substring(dimensions);

        if (PRIMITIVE_TYPE_MAP.containsKey(componentType)) {
            return PRIMITIVE_TYPE_MAP.get(componentType) + "[]".repeat(dimensions);
        } else {
            return componentType + "[]".repeat(dimensions);
        }
    }
    
    private static String convertArrayNonPrimitive(String arrayType) {
        int dimensions = arrayType.lastIndexOf('[') + 1;
        String componentType = arrayType.replace("[L", "");
        componentType = componentType.replace(";", "");
        componentType = getClassShortName(componentType);
        return componentType + "[]".repeat(dimensions);
    }
	
	public static String getAccessModifierSymbol(int modifiers) {
		if (Modifier.isPublic(modifiers)) return "+";
		if (Modifier.isProtected(modifiers)) return "#";
		if (Modifier.isPrivate(modifiers)) return "-";
		return "~";
	}
	
	

}
