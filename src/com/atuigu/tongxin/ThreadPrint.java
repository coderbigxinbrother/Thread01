package com.atuigu.tongxin;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：有ABC三个线程，要求A线程打印5次，B线程打印10次，C线程打印15次，然后，再同样的顺序来第2轮。。。。。。总共来10轮
 * @author zhouyang
 * @version 创建时间：2017年8月11日  下午4:31:50
 */
/**
 * 线程间的通信，要有标记量flag
 * 
 * 三步走 1.判断标记量，阻塞 2.干活 3.重置标记量，唤醒
 */

class shareData2 {

	private int flag = 1;
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();

	public void print5(int leapint) {
		try {
			
			lock.lock();
			while (flag != 1) {
				condition1.await();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + ":" + i +","+leapint);
			}

			
			flag = 2;
			condition2.signal();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			lock.unlock();
		}

	}
	
	
	public void print10(int leapint) {
		try {
			
			lock.lock();
			while (flag != 2) {
				condition2.await();
			}
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + ":" + i+","+leapint);
			}

			
			flag = 3;
			condition3.signal();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			lock.unlock();
		}

	}
	
	public void print15(int leapint) {
		try {
			
			lock.lock();
			while (flag != 3) {
				condition3.await();
			}
			for (int i = 0; i < 15; i++) {
				System.out.println(Thread.currentThread().getName() + ":" + i+","+leapint);
			}
			flag = 1;
			condition1.signal();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			lock.unlock();
		}

	}

}

public class ThreadPrint {

	public static void main(String[] args) {

		shareData2 data2 = new shareData2();
		
		new Thread(()->{
			
			for (int i = 0; i < 10; i++) {
				data2.print5(i);
			}
		},"AA").start();
		new Thread(()->{
			
			for (int i = 0; i < 10; i++) {
				data2.print10(i);
			}
		},"BB").start();
		new Thread(()->{
			
			for (int i = 0; i < 10; i++) {
				data2.print15(i);
			}
		},"CC").start();
	}
}
