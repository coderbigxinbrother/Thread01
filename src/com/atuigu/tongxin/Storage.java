package com.atuigu.tongxin;

import java.util.ArrayList;
import java.util.List;

public class Storage {

	private static int Max_Size =10; 
	
	private List<Object> lists = new ArrayList<>();
	
	public synchronized void produce(){
		
		//1.标志量的判断 
		while(Max_Size == lists.size() ){
			//2.阻塞
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//3.干活
		lists.add(new Object());
		System.out.println(Thread.currentThread().getName()+"生产了一个产品.....库存有："+lists.size()+"个产品");
		//4.唤醒
		this.notifyAll();
	}
	
	public synchronized void consume(){
		
		//1.标志量的判断 
		while(0 == lists.size() ){
			//2.阻塞
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//3.干活
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lists.remove(0);
		System.out.println(Thread.currentThread().getName()+"消费了一个产品.....库存有："+lists.size()+"个产品");
		//4.唤醒
		this.notifyAll();
	}
	
	
	
}
