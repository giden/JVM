package com.example.CL;

public class ServiceFactory {

	public static PawelService newInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		ClassLoader myCL = new MyClassLoader();
		
		return (PawelService) myCL.loadClass("com.example.CL.PawelServiceImpl").newInstance();
	}
}