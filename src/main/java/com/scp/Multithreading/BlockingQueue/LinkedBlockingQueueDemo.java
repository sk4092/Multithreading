package com.scp.Multithreading.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		//LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>();//unbounded
		LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(3);//bounded
		ProducerForArray t1 = new ProducerForArray(blockingQueue,"Producer");
		ConsumerForArray t2 = new ConsumerForArray(blockingQueue,"Consumer");
		t1.start();
		t2.start();
		System.out.println(Thread.currentThread().getName() +" -- Completed");			
	}
}
