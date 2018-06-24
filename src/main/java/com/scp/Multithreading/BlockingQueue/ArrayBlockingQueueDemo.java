package com.scp.Multithreading.BlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);
		ProducerForArray t1 = new ProducerForArray(blockingQueue,"Producer");
		ConsumerForArray t2 = new ConsumerForArray(blockingQueue,"Consumer");
		t1.start();
		t2.start();
		System.out.println(Thread.currentThread().getName() +" -- Completed");			
	}
	}

class ProducerForArray extends Thread{
	 BlockingQueue<Integer> producerQueue= null;
	 
	 public ProducerForArray(BlockingQueue<Integer> producerQueue,String tname) {
		super(tname);
		this.producerQueue = producerQueue;
	}
	public void run(){
		while(true){
			int randomNo = ThreadLocalRandom.current().nextInt(1,150);
			try {
				producerQueue.put(randomNo);
				System.out.println("Produced -- " + randomNo  +" -- " + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
}

class ConsumerForArray extends Thread{
	 BlockingQueue<Integer> consumerQueue= null;
	 public ConsumerForArray(BlockingQueue<Integer> consumerQueue,String tname) {
		 super(tname);
		this.consumerQueue = consumerQueue;
	}


	public void run(){
			while(true){
					try {
						System.out.println(consumerQueue);
						int e = consumerQueue.take();
						TimeUnit.SECONDS.sleep(3);
						System.out.println("Consumed -- " +e +" -- " + Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}		
			}
		
	 }
}