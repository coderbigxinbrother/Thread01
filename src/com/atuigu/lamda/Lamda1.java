package com.atuigu.lamda;

import java.util.Arrays;
import java.util.List;

interface Test{
	
	public int add(int a,int b);
}

public class Lamda1 {

	public static void main(String[] args) {
		
//	    Test test = new Test() {
//			@Override
//			public int add(int a, int b) {
//				return a+b;
//			}
//		};
//		int add = test.add(1, 2);
//		System.out.println(add);
		
		 Test test = (a,b)->{return a+b;};
		 int add = test.add(1, 3);
		 System.out.println(add);
		 
		 System.out.println("----------------------");
		 
		 
		 List<Integer> asList = Arrays.asList(12,13,14,15);
		 
		
		 
		for (Integer integer : asList) {
			
			 System.out.println(integer);
		}
		 
		System.out.println("----------------");
		
		asList.forEach(System.out::println);
	}
}
