package com.atuigu.thread;



class Ticket{
	
	private int ticketnumber = 50;
	
	public synchronized void sale(){
		
		if (ticketnumber>0) {
			ticketnumber--;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+":卖出第"+(ticketnumber+1)+"张票,还剩"+ticketnumber+"张票");
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
public class ThreadTicket {

	public static void main(String[] args) {
		
		
		Ticket ticket = new Ticket();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					ticket.sale();
				}
			}
		},"AA");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					ticket.sale();
				}
			}
		},"BB");
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					ticket.sale();
				}
			}
		},"CC");
		
		t2.start();
		t1.start();
		t3.start();
	}
}
