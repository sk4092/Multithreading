package com.scp.Multithreading.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class DelayBlockinQueueDemo {
public static void main(String[] args) {
	BlockingQueue<MyDelayClass> blockingQueue = new DelayQueue<MyDelayClass>();
	ProducerForDelay t1 = new ProducerForDelay(blockingQueue,"Producer");
	ConsumerForDelay t2 = new ConsumerForDelay(blockingQueue,"Consumer");
	t1.start();
	t2.start();
	System.out.println(Thread.currentThread().getName() +" -- Completed");	
}
}

class ProducerForDelay extends Thread{
	 BlockingQueue<MyDelayClass> producerQueue= null;
	 
	 public ProducerForDelay(BlockingQueue<MyDelayClass> producerQueue,String tname) {
		super(tname);
		this.producerQueue = producerQueue;
	}
	public void run(){
		int count = 1;
		while(count<4){
			MyDelayClass delayObj = new MyDelayClass("Object "+count, 3000);
			try {
				producerQueue.put(delayObj);
				System.out.println("Produced -- " + delayObj  +" -- " + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
		}
		
		
		
	}
}

class ConsumerForDelay extends Thread{
	 BlockingQueue<MyDelayClass> consumerQueue= null;
	 public ConsumerForDelay(BlockingQueue<MyDelayClass> consumerQueue,String tname) {
		 super(tname);
		this.consumerQueue = consumerQueue;
	}


	public void run(){
		int count = 1;
			while(count<4){
					try {
						System.out.println(consumerQueue);
						MyDelayClass obj = consumerQueue.take();
						TimeUnit.SECONDS.sleep(3);
						System.out.println("Consumed -- " +obj +" -- " + Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					count ++;
			}
		
	 }
}

class MyDelayClass implements Delayed{
	
	private String name;
	private long startTime;

	
	public MyDelayClass(String name, long startTime) {
		super();
		this.name = name;
		this.startTime = startTime;
	}

	public int compareTo(Delayed d) {
	 return	(int) ((this.startTime - ((MyDelayClass) d).startTime));
		   
	}

	public long getDelay(TimeUnit unit) {
		long delay = startTime - System.currentTimeMillis();
	    return unit.convert(delay, TimeUnit.MILLISECONDS);
	}

	@Override
	public String toString() {
		return "MyDelayClass [name=" + name + ", startTime=" + startTime + "]";
	}
	
	
}