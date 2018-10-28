package com.atuigu.tongxin;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyData{
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	
	private Object obj;
	private int flag = 0; //flag = 0  沒有对象，需要写
	
	 
	public void set(Object obj) throws InterruptedException{
		
		try {
			rwLock.writeLock().lock();
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+":"+obj);
		} finally {
			// TODO: handle finally clause
			rwLock.writeLock().unlock();
		}
	}
	public void get() throws InterruptedException{
		
		try {
			rwLock.readLock().lock();
			System.out.println(Thread.currentThread().getName()+":"+this.obj);
		} finally {
			// TODO: handle finally clause
			rwLock.readLock().unlock();
		}
	}
}

public class ThreadDemo5 {
	
	public static void main(String[] args) throws InterruptedException {
		
		String s = "wangxin";
		MyData data = new MyData();
		
		
		new Thread(()->{
			try {
				data.set(s);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"WriteThread").start();
		
		TimeUnit.SECONDS.sleep((long) 0.1);
		
		for (int i = 0; i < 100; i++) {
			new Thread(()->{
				try {
					data.get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			},String.valueOf((i+1))).start();;
			
		}
		
		
		
	}
}
