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
    	System.out.println("==========DIRECT=============");
    	for(int i=0;i<10;i++) Benchmark.run();
    	System.out.println(Benchmark.run());

    	System.out.println("==========REFLECTION=============");
        try {
        	for(int i=0;i<10;i++) BenchmarkReflection.run();
        	System.out.println(BenchmarkReflection.run());

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
				| NoSuchMethodException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
