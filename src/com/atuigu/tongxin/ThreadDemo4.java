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

class MyShareData {

	private int flag = 1; // A-1 B-2 C-3

	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();

	public void print5(int loopint) throws InterruptedException {

		try {
			lock.lock();
			// 1.判断
			while (flag != 1) {
				condition1.await();
			}
			// 2.干活
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + ":" + (i + 1) + "-->" + loopint);
			}
			// 3.通知+唤醒
			flag = 2;
			condition2.signal();
		} finally {
			lock.unlock();
		}

	}

	public void print10(int loopint) throws InterruptedException {

		try {
			lock.lock();
			// 1.判断
			while (flag != 2) {
				condition2.await();
			}
			// 2.干活
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + ":" + (i + 1) + "-->" + loopint);
			}
			// 3.通知+唤醒
			flag = 3;
			condition3.signal();
		} finally {
			lock.unlock();
		}

	}

	public void print15(int loopint) throws InterruptedException {

		try {
			lock.lock();
			// 1.判断
			while (flag != 3) {
				condition3.await();
			}
			// 2.干活
			for (int i = 0; i < 15; i++) {
				System.out.println(Thread.currentThread().getName() + ":" + (i + 1) + "-->" + loopint);
			}
			// 3.通知+唤醒
			flag = 1;
			condition1.signal();
		} finally {
			lock.unlock();
		}

	}
}

public class ThreadDemo4 {

	public static void main(String[] args) {

		MyShareData myShareData = new MyShareData();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					myShareData.print5(i + 1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "A").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					myShareData.print10(i + 1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "B").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					myShareData.print15(i + 1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "C").start();
	}
}
