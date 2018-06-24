package com.scp.Multithreading.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CyclicBarrierDemo {

    //Runnable task for each thread
    private static class Task implements Runnable {

        private CyclicBarrier barrier;
        private long timeInterval;
        public Task(CyclicBarrier barrier,long timeInterval) {
            this.barrier = barrier;
            this.timeInterval = timeInterval;
        }

        public void run() {
        	
            try {
            	Thread.sleep(timeInterval);
                System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " has crossed the barrier");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (BrokenBarrierException ex) {
            	ex.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {

        //creating CyclicBarrier with 3 parties i.e. 3 Threads needs to call await()
        final CyclicBarrier cb = new CyclicBarrier(3, new Runnable(){
            public void run(){
                //This task will be executed once all thread reaches barrier
                System.out.println("All parties are arrived at barrier, lets play");
            }
        });

        //starting each of thread
        Thread t1 = new Thread(new Task(cb,1000), "Thread 1");
        Thread t2 = new Thread(new Task(cb,2000), "Thread 2");
        Thread t3 = new Thread(new Task(cb,5000), "Thread 3");

        t1.start();
        t2.start();
        t3.start();
      
    }
}

