package com.scp.Multithreading.join;

public class JoinDemoUsingThreadClass {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Thread started: " + Thread.currentThread().getName());
		Thread2 t2 = new Thread2();
		Thread1 t1 = new Thread1(t2);//Passing thread 2 object as parameter to Thread 1 constructor
		t2.setName("Thread 2");
		t1.setName("Thread 1");
		
		t1.start();
		t2.start();
		
		t1.join();
		
		System.out.println("Thread Ended: " + Thread.currentThread().getName());
	}
}

class Thread1 extends Thread {

	Thread thread;
	public Thread1(Thread t) {
		thread=t;
	}
	public void run() {
		Thread t = Thread.currentThread();
		int count =0;
		System.out.println("Thread started: " + t.getName());
		try {
			thread.join();//join thread 2 to thread 1
			
			while(count<5) {//This block will be executed after thread 2 execution 
				System.out.println("T1 Execution");
				Thread.sleep(2000);
				count++;
			}
			
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		System.out.println("Thread ended: " + t.getName());
	}
}

class Thread2 extends Thread {

	public void run() {
		Thread t = Thread.currentThread();
		int count = 0;
		System.out.println("Thread started: " + t.getName());
	
		try {
			while(count<5) {
				System.out.println("T2 Execution");
				Thread.sleep(2000);
				count++;
			}
			
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		System.out.println("Thread ended: " + t.getName());
	}
}
