package com.atuigu.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket3{
	
	private int Ticket3number = 30;
	
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
			if (Ticket3number>0) {
				System.out.println(Thread.currentThread().getName()+":卖出第"+(Ticket3number--)+"张票,还剩"+Ticket3number+"张票");
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
 */
public class ThreadTicket3 {

	public static void main(String[] args) {
		
		
		Ticket3 Ticket3 = new Ticket3();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 40; i++) {
					Ticket3.sale();
				}
			}
		},"AA");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for (int i = 0; i < 40; i++) {
					Ticket3.sale();
				}
			}
		},"BB");
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 40; i++) {
					Ticket3.sale();
				}
			}
		},"CC");
		
//		new Thread(()->{}).start();;
		
		t1.start();
		t2.start();
		t3.start();
	}
}
