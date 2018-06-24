package com.scp.Multithreading.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo2 {
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(2);
		Thread team1 = new Thread(new LatchThreadStarter("DEV Team 1", 3000, latch));
		Thread team2 = new Thread(new LatchThreadStarter("DEV Team 2", 7000, latch));
		
		team1.start();
		team2.start();
		
		try {
			latch.await();
			System.out.println("Work completed by all DEV teams Ready for QA");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class LatchThreadStarter implements Runnable {
	private String name;
	private long waitingTime;
	CountDownLatch latch;

	public LatchThreadStarter(String name, long waitingTime, CountDownLatch latch) {
		this.name = name;
		this.waitingTime = waitingTime;
		this.latch = latch;
	}

	public void run() {
		try {
			Thread.sleep(waitingTime);
			System.out.println("Workd completed by - " + name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		latch.countDown();
	}
}
