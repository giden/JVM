package com.palbecki.app;

public class JVMcrash {

	public static void main(String[] args) {

		try{
			int[] tab = new int[Integer.MAX_VALUE];
		}
		catch(OutOfMemoryError e){
			System.out.println(e);
			for(StackTraceElement line : e.getStackTrace())
			{
				System.out.println(line.toString());
			}
		}

		try{
			fun(1);
		}
		catch(StackOverflowError e){
			System.out.println("\n"+e);
		}

	}

	static void fun(int i){
		fun(i+1);
	}

}

