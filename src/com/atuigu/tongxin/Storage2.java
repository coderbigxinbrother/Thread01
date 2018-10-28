package com.atuigu.tongxin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Storage2 {

	private static int Max_Size = 10;

	private List<Object> lists = new ArrayList<>();

	private ReentrantLock lock = new ReentrantLock();
	
	private Condition full = lock.newCondition();
	
	private Condition empty = lock.newCondition();

	public void produce() {

		try {
			lock.lock();

			// 1.标志量的判断
			while (Max_Size == lists.size()) {
				// 2.阻塞
				try {
					System.out.println("仓库已满，【" + Thread.currentThread().getName() + "】： 暂时不能执行生产任务!");
					full.await();
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
			// 3.干活
			lists.add(new Object());
			System.out.println(Thread.currentThread().getName() + "生产了一个产品.....库存有：" + lists.size() + "个产品");
			// 4.唤醒
			empty.signalAll();
		} catch (Exception e) {

		} finally {
			lock.unlock();
		}

	}

	public void consume() {

		try {
			lock.lock();

			// 1.标志量的判断
			while (0 == lists.size()) {
				// 2.阻塞
				try {
					System.out.println("仓库已空，【" + Thread.currentThread().getName() + "】： 暂时不能执行消费任务!");
					empty.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// 3.干活
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lists.remove(0);
			System.out.println(Thread.currentThread().getName() + "消费了一个产品.....库存有：" + lists.size() + "个产品");
			// 4.唤醒
			full.signalAll();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			lock.unlock();
		}

	}

}
