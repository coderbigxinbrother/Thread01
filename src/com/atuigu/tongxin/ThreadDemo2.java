package com.atuigu.tongxin;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData1{
	
	private int number=0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void increment() throws InterruptedException{
		
		
		try {
			
			lock.lock();
			while (number != 0) {
				condition.await();
			}
			number++;
			System.out.println(Thread.currentThread().getName()+":"+number);
			condition.signalAll();
		} finally {
			lock.unlock();
		}
		
	}
	
	public  void decrement() throws InterruptedException{
		
	try {
		lock.lock();
		while(number == 0) {
			condition.await();
		}
		number--;
		System.out.println(Thread.currentThread().getName()+":"+number);
		condition.signalAll();
	} finally {
		lock.unlock();
	}
	
	}
}
/**
 * 题目：有两个线程，对初始值为零的一个变量进行操作，实现一个线程对该变量加一，另一个线程对该变量减一，来10轮
 * @author zhouyang
 * @version 创建时间：2017年8月11日  上午9:25:59
 */
public class ThreadDemo2 {

	
	
	public static void main(String[] args) {
		
		ShareData1 shareData1 = new ShareData1();
		new Thread(()->{

			for (int i = 0; i < 10; i++) {
				
				try {
					shareData1.increment();
				} catch (InterruptedException e) {
					
				}
			}
			
		},"AA").start();
		new Thread(()->{
			
			for (int i = 0; i < 10; i++) {
				
				try {
					shareData1.decrement();
				} catch (InterruptedException e) {
					
				}
			}
			
		},"BB").start();
		new Thread(()->{
			
			for (int i = 0; i < 10; i++) {
				
				try {
					shareData1.increment();
				} catch (InterruptedException e) {
					
				}
			}
			
		},"CC").start();
		new Thread(()->{
			
			for (int i = 0; i < 10; i++) {
				
				try {
					shareData1.decrement();
				} catch (InterruptedException e) {
					
				}
			}
			
		},"DD").start();
	}
}
