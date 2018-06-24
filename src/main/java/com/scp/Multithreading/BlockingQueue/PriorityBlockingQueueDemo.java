package com.scp.Multithreading.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class PriorityBlockingQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<MyComparable> blockingQueue = new PriorityBlockingQueue<MyComparable>(10);
		ProducerForPriority t1 = new ProducerForPriority(blockingQueue, "Producer");
		ConsumerForPriority t2 = new ConsumerForPriority(blockingQueue, "Consumer");
		t1.start();
		t2.start();
		System.out.println(Thread.currentThread().getName() + " -- Completed");
	}
}

class ProducerForPriority extends Thread {
	BlockingQueue<MyComparable> producerQueue = null;

	public ProducerForPriority(BlockingQueue<MyComparable> producerQueue, String tname) {
		super(tname);
		this.producerQueue = producerQueue;
	}

	public void run() {
			try {
				MyComparable c1 = new MyComparable(10, "Swapnil");
				MyComparable c2 = new MyComparable(1001, "Swapnil");
				MyComparable c3 = new MyComparable(10, "Sahil");
				producerQueue.put(c1);
				System.out.println("Produced -- " + c1 + " -- " + Thread.currentThread().getName());
				producerQueue.put(c2);
				System.out.println("Produced -- " + c2 + " -- " + Thread.currentThread().getName());
				producerQueue.put(c3);
				System.out.println("Produced -- " + c3 + " -- " + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}

class ConsumerForPriority extends Thread {
	BlockingQueue<MyComparable> consumerQueue = null;

	public ConsumerForPriority(BlockingQueue<MyComparable> consumerQueue, String tname) {
		super(tname);
		this.consumerQueue = consumerQueue;
	}

	public void run() {
		while (true) {
			try {
				System.out.println(consumerQueue);
				TimeUnit.SECONDS.sleep(1);
				MyComparable c1 = consumerQueue.take();
				System.out.println("Consumed -- " + c1 + " -- " + Thread.currentThread().getName());
				MyComparable c2 = consumerQueue.take();
				System.out.println("Consumed -- " + c1 + " -- " + Thread.currentThread().getName());
				MyComparable c3 = consumerQueue.take();
				System.out.println("Consumed -- " + c1 + " -- " + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}

class MyComparable implements Comparable<MyComparable>{

	int id;
	String name;
	
	public MyComparable(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int compareTo(MyComparable o) {
		int result;
		result = this.id>o.id?1:-1;
		if(result!=0)
			return result;
		else {
			return this.name.compareTo(o.name);
		}
	}
	
}