package com.scp.Multithreading.BlockingQueue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TransferQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<Integer> blockingQueue = new LinkedTransferQueue<Integer>();
		ProducerForTransfer t1 = new ProducerForTransfer(blockingQueue,"Producer");
		ConsumerForTransfer t2 = new ConsumerForTransfer(blockingQueue,"Consumer");
		t1.start();
		t2.start();
		System.out.println(Thread.currentThread().getName() +" -- Completed");			
	}
}

class ProducerForTransfer extends Thread{
	LinkedTransferQueue<Integer> producerQueue= null;
	 
	 public ProducerForTransfer(LinkedTransferQueue<Integer> producerQueue,String tname) {
		super(tname);
		this.producerQueue = producerQueue;
	}
	public void run(){
		int count=0;
		while(count<10){
			int randomNo = ThreadLocalRandom.current().nextInt(1,150);
				producerQueue.put(randomNo);
				System.out.println("Produced -- " + randomNo  +" -- " + Thread.currentThread().getName());
			count++;
		}
		
		
		
	}
}

class ConsumerForTransfer extends Thread{
	LinkedTransferQueue<Integer> consumerQueue= null;
	 public ConsumerForTransfer(LinkedTransferQueue<Integer> consumerQueue,String tname) {
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