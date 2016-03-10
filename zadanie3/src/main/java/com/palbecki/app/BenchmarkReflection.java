package com.palbecki.app;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BenchmarkReflection {
	
	static final int M = 10000;
	static long start;
	static long time;
	static int formed;
	static String name;
	
	static List<Long> simpleRead = new ArrayList<>();
	static List<Long> refRead = new ArrayList<>();
	static List<Long> simpleWrite = new ArrayList<>();
	static List<Long> refWrite = new ArrayList<>();
	static List<Long> method = new ArrayList<>();


	public static String run() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
		Band band = new Band("test", 1234);
		for(int i=0;i<M;i++){
			start = System.nanoTime();
			Field f = band.getClass().getField("formed");
			formed = (int) f.get(band);
			simpleRead.add(System.nanoTime() - start);
		}

		for(int i=0;i<M;i++){
			start = System.nanoTime();
			Field f = band.getClass().getField("name");
			name = (String) f.get(band);
			refRead.add(System.nanoTime() - start);
		}

		for(int i=0;i<M;i++){
			start = System.nanoTime();
			Field f = band.getClass().getField("formed");
			f.set(band,4321);
			simpleWrite.add(System.nanoTime() - start);
		}
		for(int i=0;i<M;i++){
			start = System.nanoTime();
			Field f = band.getClass().getField("name");
			f.set(band,"test");
			refWrite.add(System.nanoTime() - start);
		}
		for(int i=0;i<M;i++){
			start = System.nanoTime();
			Method m =  band.getClass().getMethod("Hello", String.class);
			m.invoke(band, "test");
			method.add(System.nanoTime() - start);
		}
		
		Collections.sort(simpleRead);
		for(int i=0;i<100;i++){
			simpleRead.remove(simpleRead.get(i));
			simpleRead.remove(simpleRead.get(simpleRead.size()-i-1));
		}
		Collections.sort(refRead);
		for(int i=0;i<100;i++){
			refRead.remove(refRead.get(i));
			refRead.remove(refRead.get(refRead.size()-i-1));
		}
		Collections.sort(simpleWrite);
		for(int i=0;i<100;i++){
			simpleWrite.remove(simpleWrite.get(i));
			simpleWrite.remove(simpleWrite.get(simpleWrite.size()-i-1));
		}
		Collections.sort(refWrite);
		for(int i=0;i<100;i++){
			refWrite.remove(refWrite.get(i));
			refWrite.remove(refWrite.get(refWrite.size()-i-1));
		}
		Collections.sort(method);
		for(int i=0;i<100;i++){
			method.remove(method.get(i));
			method.remove(method.get(method.size()-i-1));
		}

		StringBuilder sb = new StringBuilder();
		
		sb.append("SimpleRead "+(int)simpleRead.stream().mapToLong(val -> val).average().getAsDouble());
		sb.append("\nRefRead "+(int)refRead.stream().mapToLong(val -> val).average().getAsDouble());
		sb.append("\nSimpleWrite "+(int)simpleWrite.stream().mapToLong(val -> val).average().getAsDouble());
		sb.append("\nRefWrite "+(int)refWrite.stream().mapToLong(val -> val).average().getAsDouble());
		sb.append("\nMethod "+(int)method.stream().mapToLong(val -> val).average().getAsDouble());

		return sb.toString();
	}

}
