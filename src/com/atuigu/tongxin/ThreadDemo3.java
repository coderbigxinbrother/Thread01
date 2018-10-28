package com.atuigu.tongxin;

import java.util.concurrent.TimeUnit;
//static synchronized
class Phone
{
	public static synchronized void getIOS() throws Exception
	{
		TimeUnit.SECONDS.sleep(2);
		System.out.println("*****getIOS");
	}
	public synchronized void getAndroid()throws Exception
	{
		System.out.println("*****getAndroid");
	}
	public void getHello()
	{
		System.out.println("*****getHello");
	}
}

/**
 * 1	标准访问，先打印苹果还是android？ 
 * 2	睡觉2秒钟，先打印苹果还是android？
 * 3	新增hello方法，先打印苹果还是hello？
 * 4	有两部手机，先打印苹果还是android？
 * 5	两个静态同步方法，有一部手机，先打印苹果还是android？
 * 6	两个静态同步方法，有2部手机，先打印苹果还是android？
 * 7	一个静态同步方法，一个普通同步方法，有一部手机，先打印苹果还是android？
 * 8	一个静态同步方法，一个普通同步方法，有2部手机，先打印苹果还是android？
 * 
 * 题目：线程8锁
 *
 *一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了，
 *其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
 *
锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法
锁的是当前模板class

 * 
 */
public class ThreadDemo3 
{
	public static void main(String[] args)
	{
		Phone phone = new Phone();
		Phone phone2 = new Phone();
		
		new Thread(() ->
		{
			try 
			{
				phone.getIOS();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}, "AA").start();
		
		
		new Thread(() ->
		{
			try 
			{
			     //phone.getAndroid();
				//phone.getHello();
				phone2.getAndroid();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}, "BB").start();
		
		
	}

}
