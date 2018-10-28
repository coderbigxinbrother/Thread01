package com.atuigu.tongxin;




class shareData{
	
	private int number=0;
	
	public synchronized void increment() throws InterruptedException{
		
		while(number != 0) {
			this.wait();
		}
		number++;
		System.out.println(Thread.currentThread().getName()+":"+number);
		this.notifyAll();
	}
	
	public synchronized void decrement() throws InterruptedException{
		
		
		while(number == 0) {
			this.wait();
		}
		number--;
		System.out.println(Thread.currentThread().getName()+":"+number);
		this.notifyAll();
	}
}
/**
 * 题目：有两个线程，对初始值为零的一个变量进行操作，实现一个线程对该变量加一，另一个线程对该变量减一，来10轮
 * @author zhouyang
 * @version 创建时间：2017年8月11日  上午9:25:59
 */
public class ThreadDemo {

	
	
	public static void main(String[] args) {
		
		shareData shareData = new shareData();
		new Thread(()->{

			for (int i = 0; i < 10; i++) {
				
				try {
					shareData.increment();
				} catch (InterruptedException e) {
					
				}
			}
			
		},"AA").start();
		new Thread(()->{
			
			for (int i = 0; i < 10; i++) {
				
				try {
					shareData.decrement();
				} catch (InterruptedException e) {
					
				}
			}
			
		},"BB").start();
		new Thread(()->{
			
			for (int i = 0; i < 10; i++) {
				
				try {
					shareData.increment();
				} catch (InterruptedException e) {
					
				}
			}
			
		},"CC").start();
		new Thread(()->{
			
			for (int i = 0; i < 10; i++) {
				
				try {
					shareData.decrement();
				} catch (InterruptedException e) {
					
				}
			}
			
		},"DD").start();
	}
}
