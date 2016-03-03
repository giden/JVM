package com.example.CL;

public class Main {

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			InterruptedException {
		
		PawelService s0, s1, s2;
		s0 = new PawelServiceImpl();
		System.out.println("Ss0: " + s0.getClass().getClassLoader());

		s1 = ServiceFactory.newInstance();
		System.out.println("Ss2: " + s1.getClass().getClassLoader());


		System.out.println("0. " + s0.message());
		System.out.println("1. " + s1.message());
		
		while (true) {
			s2 = ServiceFactory.newInstance();
			System.out.println("2. " + s2.message());
			
			Thread.sleep(2000);
		}

	}

}
