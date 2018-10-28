package com.atuigu.executor;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author wangxin
 * 
 *         接口 executor接口 线程池的接口 子接口 executorService
 * 
 *         对象的产生 executors.newFixedThreadPool()
 *
 */
public class ThreadExecutors {

	public static void main(String[] args) {
		ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
		ScheduledFuture<Integer> future = null;
		
		try {
			for (int i = 0; i < 20; i++) {

				future = threadPool.schedule(() -> {
					System.out.print(Thread.currentThread().getName() + ",");
					return new Random().nextInt();
				},2,TimeUnit.SECONDS);
				System.out.println("result^^^^" + future.get());
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			threadPool.shutdown();
		}
		


	}

	public static void threadpoolTest() {
		// ExecutorService threadPool = Executors.newFixedThreadPool(5);
		// ExecutorService threadPool = Executors.newSingleThreadExecutor();
		ExecutorService threadPool = Executors.newCachedThreadPool();

		Future<Integer> future = null;

		try {
			for (int i = 0; i < 200; i++) {

				future = threadPool.submit(() -> {
					System.out.print(Thread.currentThread().getName() + ",");
					return new Random().nextInt();
				});
				System.out.println("result^^^^" + future.get());
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			threadPool.shutdown();
		}
	}

}
