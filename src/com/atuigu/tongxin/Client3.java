package com.atuigu.tongxin;

public class Client3 {

     public static void main(String[] args) {
		
      Storage3 storage = new Storage3();
    	 
       Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 20; i++) {
					storage.produce();
				}
			}
		},"t1");
       
       
       Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 20; i++) {
					storage.consume();
				}
			}
		},"t2");
       Thread t3 = new Thread(new Runnable() {
    	   
    	   @Override
    	   public void run() {
    		   // TODO Auto-generated method stub
    		   for (int i = 0; i < 20; i++) {
    			   storage.produce();
    		   }
    	   }
       },"t3");
       Thread t4 = new Thread(new Runnable() {
    	   
    	   @Override
    	   public void run() {
    		   // TODO Auto-generated method stub
    		   for (int i = 0; i < 20; i++) {
    			   storage.consume();
    		   }
    	   }
       },"t4");
       
       t1.start();
       t2.start();
       t3.start();
       t4.start();
	}
}
