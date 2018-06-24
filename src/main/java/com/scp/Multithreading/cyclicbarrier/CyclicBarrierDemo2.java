package com.scp.Multithreading.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo2 {

	public static void main(String[] args) {
		BarrierAction action = new BarrierAction();
		CyclicBarrier barrier = new CyclicBarrier(2, action);

		BarrierThreadHandler barrierThreadHandler1 = new BarrierThreadHandler(barrier, 1000);
		barrierThreadHandler1.setName("Dev Team 1");
		BarrierThreadHandler barrierThreadHandler2 = new BarrierThreadHandler(barrier, 3000);
		barrierThreadHandler2.setName("Dev Team 2");

		barrierThreadHandler1.start();
		barrierThreadHandler2.start();

		// Reusing the same barrier
		/*
		 * BarrierThreadHandler barrierThreadHandler3 = new
		 * BarrierThreadHandler(barrier, 1000);
		 * barrierThreadHandler3.setName("Dev Team 3"); BarrierThreadHandler
		 * barrierThreadHandler4 = new BarrierThreadHandler(barrier, 3000);
		 * barrierThreadHandler4.setName("Dev Team 4");
		 * 
		 * barrierThreadHandler3.start(); barrierThreadHandler4.start();
		 */
	}
}

class BarrierThreadHandler extends Thread {
	CyclicBarrier barrier;
	private long time;

	public BarrierThreadHandler(CyclicBarrier barrier, long time) {
		this.barrier = barrier;
		this.time = time;
	}

	@Override
	public void run() {

		try {
			Thread.sleep(time);
			System.out.println("Task completed by - " + Thread.currentThread().getName());
			barrier.await();
			System.out.println(Thread.currentThread().getName() + "'s Work Assigned to QA team");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class BarrierAction implements Runnable {

	public void run() {
		System.out.println("Task Completed by All DEV teams");
	}

}
