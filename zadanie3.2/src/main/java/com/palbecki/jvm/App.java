package com.palbecki.jvm;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	try {
			System.out.println(Converter.toJson(new Band("test",1234)));
			
			ObjectMapper mapper = new ObjectMapper();
			System.out.println(mapper.writeValueAsString(new Band("test",1234)));
			
			
			for(int i=0;i<10;i++)Benchmark.run();
			System.out.println(Benchmark.run());
			
		} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
