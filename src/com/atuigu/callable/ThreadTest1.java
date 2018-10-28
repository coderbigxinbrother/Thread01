package com.atuigu.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class MyThread implements Runnable{

	@Override
	public void run() {
		System.out.println("实现runnable接口");
	}
	
} 
class MyThread2 implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("实现Callable接口");
		return 100;
	}

} 



public class ThreadTest1 {

	public static void main(String[] args) {
		
		Thread thread = new Thread(new MyThread());
		thread.start();
		
		FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
		Thread thread2 = new Thread(futureTask);
		thread2.start();
	}
}
