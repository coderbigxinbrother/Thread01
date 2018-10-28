package com.atuigu.tongxin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Storage3 {

	private static int Max_Size = 10;

//	private List<Object> lists = new ArrayList<>();
	
	// 仓库存储的载体
    private LinkedBlockingQueue<Object> lists = new LinkedBlockingQueue<Object>(10);  

	public void produce() {

		

			// 1.标志量的判断
			if(Max_Size == lists.size()) {
				// 2.阻塞
				System.out.println("仓库已满，【" + Thread.currentThread().getName() + "】： 暂时不能执行生产任务!");
				
			}
			
			// 3.干活
			try {
				lists.put(new Object());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "生产了一个产品.....库存有：" + lists.size() + "个产品");

		

	}

	public void consume() {

			// 1.标志量的判断
			if (0 == lists.size()) {
				System.out.println("仓库已空，【" + Thread.currentThread().getName() + "】： 暂时不能执行消费任务!");	
			}
			// 3.干活
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				lists.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "消费了一个产品.....库存有：" + lists.size() + "个产品");

	}

}
