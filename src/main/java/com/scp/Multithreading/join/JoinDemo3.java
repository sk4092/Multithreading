package com.scp.Multithreading.join;
/**
 * 
 * this example is not working 
 * 
 * */
public class JoinDemo3 {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Thread started: " + Thread.currentThread().getName());
		
		Thread t1 = new Thread(new MyRunnableX(), "Thread 1");
		Thread t2 = new Thread(new MyRunnableY(), "Thread 2");
		
		t1.start();
		t2.start();
		//t1.join();
		
		synchronized (t1) {
			System.out.println("Inside synch --------------- " + Thread.currentThread().getName());
			if(Thread.currentThread().getName().equals("Thread 1"))
			{
				System.out.println("Joining t2------------------------------");
				t2.join();
			}
				
		}
		
		
		System.out.println("Thread Ended: " + Thread.currentThread().getName());
	}
}

class MyRunnableX implements Runnable {

	/*Thread thread;
	public MyRunnableX(Thread t) {
		thread=t;
	}*/
	public void run() {
		Thread t = Thread.currentThread();
		int count=0;
		System.out.println("Thread started: " + t.getName());
		
		try {
			//thread.join();
			
			while(count<10) {
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

class MyRunnableY implements Runnable {

	public void run() {
		Thread t = Thread.currentThread();
		int count = 0;
		System.out.println("Thread started: " + t.getName());
	
		try {
			
			while(count<10) {
				Thread.sleep(1000);
				System.out.println("Thread 2 Execution");
				count ++;
			}
			
			
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		System.out.println("Thread ended: " + t.getName());
	}
}