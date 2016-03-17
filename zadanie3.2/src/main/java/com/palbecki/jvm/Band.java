package com.palbecki.jvm;

import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Band {
	
	public String name;
	private int formed;
	public List<String> lista = Arrays.asList("a","v","c");
	
	public Band(String name, int formed){
		this.formed = formed;
		this.name = name;
	}
	
	public String Hello(String hello){
		return hello;
	}

}
