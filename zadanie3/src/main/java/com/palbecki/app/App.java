package com.palbecki.app;

import java.lang.reflect.InvocationTargetException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	for(int i=0;i<100;i++) Benchmark.run();
    	for(int i=0;i<10;i++){
    		System.out.println(Benchmark.run());
    		System.out.println("=================");
    		
    	}

        System.out.println("==========REFLECTION=============");
        try {
        	for(int i=0;i<100;i++) BenchmarkReflection.run();
        	for(int i=0;i<10;i++){
        		System.out.println(BenchmarkReflection
        				.run());
        		System.out.println("=================");
        		
        	}

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
				| NoSuchMethodException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
