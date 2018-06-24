package com.scp.Multithreading.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ConcurrentLinkedQueueDemo {
public static void main(String[] args) {
	ConcurrentLinkedQueue<Integer> blockingQueue = new ConcurrentLinkedQueue<Integer>();//bounded
	ProducerForConcurrentLQ t1 = new ProducerForConcurrentLQ(blockingQueue,"Producer");
	ConsumerForConcurrentLQ t2 = new ConsumerForConcurrentLQ(blockingQueue,"Consumer");
	t1.start();
	t2.start();
	System.out.println(Thread.currentThread().getName() +" -- Completed");			
}
}

class ProducerForConcurrentLQ extends Thread{
	ConcurrentLinkedQueue<Integer> producerQueue= null;
	 
	 public ProducerForConcurrentLQ(ConcurrentLinkedQueue<Integer> blockingQueue,String tname) {
		super(tname);
		this.producerQueue = blockingQueue;
	}
	public void run(){
		while(true){
			int randomNo = ThreadLocalRandom.current().nextInt(1,150);
				producerQueue.offer(randomNo);
				System.out.println("Produced -- " + randomNo  +" -- " + Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
}

class ConsumerForConcurrentLQ extends Thread{
	ConcurrentLinkedQueue<Integer> consumerQueue= null;
	 public ConsumerForConcurrentLQ(ConcurrentLinkedQueue<Integer> blockingQueue,String tname) {
		 super(tname);
		this.consumerQueue = blockingQueue;
	}


	public void run(){
			while(true){
					try {
						System.out.println(consumerQueue);
						int e = consumerQueue.peek();
						TimeUnit.SECONDS.sleep(3);
						System.out.println("Consumed -- " +e +" -- " + Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}		
			}
		
	 }
}
