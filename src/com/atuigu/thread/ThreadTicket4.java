package com.atuigu.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket4{
	
	private int ticket4number = 30;
	
	private Lock lock = new ReentrantLock();
	
	public void sale(){
		
		try {
			lock.lock();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (ticket4number>0) {
				System.out.println(Thread.currentThread().getName()+":卖出第"+(ticket4number--)+"张票,还剩"+ticket4number+"张票");
			}
			
		} finally {
			
			lock.unlock();
		}
		
		
	}
}
/**
 * 
 * @author wangxin
 * 
 * 线程操作资源类
 * 
 * 线程     操作   资源类
 *
 *  卖票案例
 *  
 *  lamda 表达式 
 *  ()->{}
 */
public class ThreadTicket4 {

	public static void main(String[] args) {
		
		
		Ticket4 Ticket4 = new Ticket4();
		
//		Thread t1 = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				for (int i = 0; i < 40; i++) {
//					Ticket4.sale();
//				}
//			}
//		},"AA");
//		
//		Thread t2 = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				
//				for (int i = 0; i < 40; i++) {
//					Ticket4.sale();
//				}
//			}
//		},"BB");
//		
//		Thread t3 = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				for (int i = 0; i < 40; i++) {
//					Ticket4.sale();
//				}
//			}
//		},"CC");
//		t1.start();
//		t2.start();
//		t3.start();
		
		new Thread(()->{for (int i = 0; i < 40; i++) {Ticket4.sale();}},"AA").start();
		new Thread(()->{for (int i = 0; i < 40; i++) {Ticket4.sale();}},"BB").start();
		new Thread(()->{for (int i = 0; i < 40; i++) {Ticket4.sale();}},"CC").start();
		
	}
}
