package com.palbecki.jvm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

public class Benchmark {
	
	
	static final int M = 1000;
	static final int N = 10000;


	static long start;
	static long time;
	static int formed;
	static String name;
	
	static List<Long> reflection = new ArrayList<>();
	static List<Long> jackson = new ArrayList<>();


	public static String run() throws IllegalArgumentException, IllegalAccessException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		Band band = new Band("test", 1234);
		for(int i=0;i<M;i++){
			start = System.nanoTime();
			for(int j=0;j<N;j++){
			Converter.toJson(band);
			}
			reflection.add(System.nanoTime() - start);
		}

		for(int i=0;i<M;i++){
			start = System.nanoTime();
			for(int j=0;j<N;j++){
				mapper.writeValueAsString(band);
			}
			jackson.add(System.nanoTime() - start);
		}

		
		Collections.sort(reflection);
		for(int i=0;i<100;i++){
			reflection.remove(i);
			reflection.remove(reflection.size()-i-1);
		}
		Collections.sort(jackson);
		for(int i=0;i<100;i++){
			jackson.remove(i);
			jackson.remove(reflection.size()-i-1);
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("reflection "+(int)(reflection.stream().mapToLong(val -> val).average().getAsDouble()));
		sb.append("\njackson "+(int)(jackson.stream().mapToLong(val -> val).average().getAsDouble()));
		
		return sb.toString();
	}

}
