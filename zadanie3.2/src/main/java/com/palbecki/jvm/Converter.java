package com.palbecki.jvm;

import java.lang.reflect.Field;

public class Converter {

	public static String toJson(Object o) throws IllegalArgumentException, IllegalAccessException {
		Class objectClass = o.getClass();

		Field[] field = objectClass.getDeclaredFields();
		StringBuilder sb = new StringBuilder("{");

		for(int i=0;i<field.length;i++) {
			sb.append("\"");
			sb.append(field[i].getName());
			sb.append("\":");
			boolean test = field[i].get(o).getClass().equals(String.class);
			if(test)
				sb.append("\"");
			sb.append(field[i].get(o));
			if(test)
				sb.append("\"");
			if(i < field.length-1) 
				sb.append(",");
			else
				sb.append("}");
		}
		return sb.toString();		
	}

}
