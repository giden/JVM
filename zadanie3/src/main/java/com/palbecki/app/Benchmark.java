package com.palbecki.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Benchmark {
	
	
	static final int M = 1000;
	static final int N = 10000;


	static long start;
	static long time;
	static int formed;
	static String name;
	
	static List<Long> simpleRead = new ArrayList<>();
	static List<Long> refRead = new ArrayList<>();
	static List<Long> simpleWrite = new ArrayList<>();
	static List<Long> refWrite = new ArrayList<>();
	static List<Long> method = new ArrayList<>();


	public static String run(){
		Band band = new Band("test", 1234);
		for(int i=0;i<M;i++){
			start = System.nanoTime();
			for(int j=0;j<N;j++){
			formed = band.formed;
			}
			simpleRead.add(System.nanoTime() - start);
		}

		for(int i=0;i<M;i++){
			start = System.nanoTime();
			for(int j=0;j<N;j++){
			name = band.name;
			}
			refRead.add(System.nanoTime() - start);
		}

		for(int i=0;i<M;i++){	
			start = System.nanoTime();
			for(int j=0;j<N;j++){
			band.formed = 4321;
			}
			simpleWrite.add(System.nanoTime() - start);
		}
		for(int i=0;i<M;i++){
			start = System.nanoTime();
			for(int j=0;j<N;j++){
			band.name = "test";
			}
			refWrite.add(System.nanoTime() - start);
		}
		for(int i=0;i<M;i++){
			start = System.nanoTime();
			for(int j=0;j<N;j++){
			band.Hello("test");
			}
			method.add(System.nanoTime() - start);
		}
		Collections.sort(simpleRead);
		for(int i=0;i<100;i++){
			simpleRead.remove(i);
			simpleRead.remove(simpleRead.size()-i-1);
		}
		Collections.sort(refRead);
		for(int i=0;i<100;i++){
			refRead.remove(i);
			refRead.remove(simpleRead.size()-i-1);
		}
		Collections.sort(simpleWrite);
		for(int i=0;i<100;i++){
			simpleWrite.remove(i);
			simpleWrite.remove(simpleRead.size()-i-1);
		}
		Collections.sort(refWrite);
		for(int i=0;i<100;i++){
			refWrite.remove(i);
			refWrite.remove(simpleRead.size()-i-1);
		}
		Collections.sort(method);
		for(int i=0;i<100;i++){
			method.remove(i);
			method.remove(simpleRead.size()-i-1);
		}

		
		StringBuilder sb = new StringBuilder();
		
		
		sb.append("SimpleRead "+(int)(simpleRead.stream().mapToLong(val -> val).average().getAsDouble()));
		sb.append("\nRefRead "+(int)(refRead.stream().mapToLong(val -> val).average().getAsDouble()));
		sb.append("\nSimpleWrite "+(int)(simpleWrite.stream().mapToLong(val -> val).average().getAsDouble()));
		sb.append("\nRefWrite "+(int)(refWrite.stream().mapToLong(val -> val).average().getAsDouble()));
		sb.append("\nMethod "+(int)(method.stream().mapToLong(val -> val).average().getAsDouble()));

		return sb.toString();
	}

}
