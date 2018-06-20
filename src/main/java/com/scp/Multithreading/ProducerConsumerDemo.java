package com.scp.Multithreading;

import java.util.LinkedList;
import java.util.Random;


public class ProducerConsumerDemo 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	final ProdConsume prodConsume = new ProdConsume();
    	
        Thread producer1 = new Thread(new Runnable() {
		
            public void run()
            {
                try
                {
                	prodConsume.produce("Producer 1");
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
		});
        
        Thread producer2 = new Thread(new Runnable() {
    		
            public void run()
            {
                try
                {
                	prodConsume.produce("Producer 2");
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
		});
        
        Thread consumer1 = new Thread(new Runnable() {
			
			public void run() {
				 try
	                {
	                    prodConsume.consume("Consumer 1");
	                }
	                catch(InterruptedException e)
	                {
	                    e.printStackTrace();
	                }
			}
		});
        
 Thread consumer2 = new Thread(new Runnable() {
			
			public void run() {
				 try
	                {
	                    prodConsume.consume("Consumer 2");
	                }
	                catch(InterruptedException e)
	                {
	                    e.printStackTrace();
	                }
			}
		});
        
        producer1.start();
        consumer1.start();
        producer2.start();
        consumer2.start();

    }
    
    
    public static class ProdConsume{
    	
    	 LinkedList<Integer> list = new LinkedList<Integer>();
         int capacity = 5;
    	
    	public void produce(String name) throws InterruptedException {
    		int val;
    		while(true) {
    			synchronized(this) {
    				val = new Random().nextInt(100);
    				while(list.size()==capacity) {
    					wait();
    				}
    				
    				System.out.println(name +" produced Value "+val);
    				list.add(val);
    				notifyAll();
    				Thread.sleep(1000);
    			}
        	}
    	}
    	
    	public void consume(String name) throws InterruptedException {
    		while(true) {
    			synchronized (this) {
					while(list.size()==0) {
						wait();
					}
						
					
					int val = list.removeFirst();
					System.out.println("Consumed by "+name+" Value "+val);
					notifyAll();
					Thread.sleep(1000);
				}
    		}
    	}
    }

}

