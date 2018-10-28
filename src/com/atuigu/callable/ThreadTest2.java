package com.atuigu.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		
//		FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
		FutureTask<Integer> futureTask = new FutureTask<Integer>(()->{
			
			System.out.println("耗时操作......");
			return 200;
		});
		Thread thread2 = new Thread(futureTask,"AA");
		thread2.start();
		Integer integer = futureTask.get();
		
		System.out.println(integer);
		
	}
}
