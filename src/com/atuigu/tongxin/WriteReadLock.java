package com.atuigu.tongxin;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyShareData2{
	
	private Object obj;
	private ReentrantReadWriteLock lock= new ReentrantReadWriteLock();
	
	public void set(Object object){
		
		try {
			lock.writeLock().lock();
			System.out.println("设置数据.....");
			this.obj = object;
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.writeLock().unlock();
		}
	}
	
	public void get(){
		
		try {
			lock.readLock().lock();
			System.out.println(Thread.currentThread().getName()+":"+obj);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.readLock().unlock();
		}
	}
}

public class WriteReadLock {

	public static void main(String[] args) {
		
	     MyShareData2 data2 = new MyShareData2();
	     String ss = "abc";
	     new Thread(()->{
	    	 data2.set(ss); 
	     },"writeThread").start();
	     
	     for (int i = 0; i < 100; i++) {
	    	 new Thread(()->{
	    		 data2.get();
	    	 },String.valueOf(i)).start();
		}
	     
	     
	}
}
