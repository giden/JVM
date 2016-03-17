package com.palbecki.jvm;

import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Band {
	
	public String name;
	private int formed;
	private List<String> lista = Arrays.asList("a","v","c");
	private String[] tab = {"a","v","c"};

	
	public Band(String name, int formed){
		this.formed = formed;
		this.name = name;
	}
	
	public String Hello(String hello){
		return hello;
	}

}
