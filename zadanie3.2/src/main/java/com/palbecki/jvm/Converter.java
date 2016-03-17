package com.palbecki.jvm;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;

public class Converter {

	public static String toJson(Object o) throws IllegalArgumentException, IllegalAccessException {
		Class objectClass = o.getClass();

		Field[] field = objectClass.getDeclaredFields();
		StringBuilder sb = new StringBuilder("{");

		for(int i=0;i<field.length;i++) {
			field[i].setAccessible(true);

			if(field[i].get(o) instanceof Object[]) {
				sb.append("\"");
				sb.append(field[i].getName());
				sb.append("\"");
				sb.append(":");
				sb.append("[");
				int len = Array.getLength(field[i].get(o));
				for(int j=0;j<len;j++){
					boolean test = Array.get(field[i].get(o), j).getClass().equals(String.class);
					if(test)
						sb.append("\"");
					sb.append(Array.get(field[i].get(o), j));
					if(test)
						sb.append("\"");
					if(j<len-1)
						sb.append(",");
				}
				sb.append("]");
			}else			
				if (field[i].get(o) instanceof Collection) {
					Iterator items = ((Collection) field[i].get(o)).iterator();
					sb.append("\"");
					sb.append(field[i].getName());
					sb.append("\"");
					sb.append(":");
					sb.append("[");
					while (items != null && items.hasNext()) {
						Object item = items.next();

						boolean test = item.getClass().equals(String.class);
						if(test)
							sb.append("\"");
						sb.append(item);
						if(test)
							sb.append("\"");

						if(items.hasNext())sb.append(",");
					}
					sb.append("]");
				}
				else{

					sb.append("\"");
					sb.append(field[i].getName());
					sb.append("\":");
					boolean test = field[i].get(o).getClass().equals(String.class);
					if(test)
						sb.append("\"");
					sb.append(field[i].get(o));
					if(test)
						sb.append("\"");
				}
			if(i < field.length-1) 
				sb.append(",");
			else
				sb.append("}");

		}
		return sb.toString();		
	}

}
