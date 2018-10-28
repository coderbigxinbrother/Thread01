package com.atuigu.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket2{
	
	private int Ticket2number = 30;
	
	private Lock lock = new ReentrantLock();
	
	public void sale(){
		
		try {
			lock.lock();
			
			if(Ticket2number>0) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Ticket2number--;
				System.out.println(Thread.currentThread().getName()+":卖出第"+(Ticket2number+1)+"张票,还剩"+Ticket2number+"张票");
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
public class ThreadTicket2 {

	public static void main(String[] args) {
		
		
		Ticket2 Ticket2 = new Ticket2();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					Ticket2.sale();
				}
				
			}
		},"AA");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					Ticket2.sale();
				}
			}
		},"BB");
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					Ticket2.sale();
				}
			}
		},"CC");
		
		t1.start();
		t2.start();
		t3.start();
	}
}
