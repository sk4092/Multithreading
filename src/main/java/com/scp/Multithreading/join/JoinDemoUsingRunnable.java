package com.scp.Multithreading.join;

public class JoinDemoUsingRunnable {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Thread started: " + Thread.currentThread().getName());
		Thread t2 = new Thread(new MyRunnable2(), "Thread 2");
		Thread t1 = new Thread(new MyRunnable1(t2), "Thread 1");
		
		t1.start();
		t2.start();
		t1.join();
		System.out.println("Thread Ended: " + Thread.currentThread().getName());
	}
}

class MyRunnable1 implements Runnable {

	Thread thread;
	public MyRunnable1(Thread t) {
		thread=t;
	}
	public void run() {
		Thread t = Thread.currentThread();
		int count=0;
		System.out.println("Thread started: " + t.getName());
		try {
			thread.join();//join thread 2 to thread 1
			
			while(count<5) {//This block will be executed after thread 2 execution 
				System.out.println("Thread1 Execution");
				Thread.sleep(2000);
				count++;
			}
			
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		System.out.println("Thread ended: " + t.getName());
	}
}

class MyRunnable2 implements Runnable {

	public void run() {
		Thread t = Thread.currentThread();
		int count=0;
		System.out.println("Thread started: " + t.getName());
	
		try {
			while(count<5) {
				System.out.println("Thread2 Execution");
				Thread.sleep(2000);
				count++;
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		System.out.println("Thread ended: " + t.getName());
	}
}