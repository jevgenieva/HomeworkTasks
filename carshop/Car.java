package carshop;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Car {
	
	private ThreadPoolExecutor pool;
	
	Car() throws InterruptedException {
		long l = java.lang.System.currentTimeMillis();
		
		pool = carPool();
		for (Runnable task : pool.getQueue()) {
			task.run();
		}
		pool.shutdown();
		
		while(true) {
		if(pool.isTerminated()) 
			break;
		}
		
		System.out.println("The car is assembled in " + (java.lang.System.currentTimeMillis() - l)/1000.0 + " seconds");
	}
	
	private ThreadPoolExecutor carPool() {
		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
		for (int i = 0; i < 4; i++) {
			pool.submit(new Tire());
		}
		for (int i = 0; i < 5; i++) {
			pool.submit(new Seat());
		}
		
		pool.submit(new Engine());
		pool.submit(new Frame());

		return pool;
	}

	public static class Tire implements Runnable{
		@Override
		public void run() {
			try {
				Thread.sleep(2000);
				System.out.println("a new tire is created");
			} catch (InterruptedException e) {
				System.out.println("Not enough rubber to make a tire.");
			}
		}
	}
	
	public static class Seat implements Runnable{
		@Override
		public void run() {
			try {
				Thread.sleep(3000);
				System.out.println("a new seat is created");
			} catch (InterruptedException e) {
				System.out.println("Not enough leather to make a seat.");
			}
		}
	}
	
	public static class Engine implements Runnable{
		@Override
		public void run() {
			try {
				Thread.sleep(7000);
				System.out.println("a new engine is created");
			} catch (InterruptedException e) {
				System.out.println("Not enough magic to make an engine.");
			}
		}
	}
	
	public static class Frame implements Runnable{
		@Override
		public void run() {
			try {
				Thread.sleep(2000);
				System.out.println("a new frame is created");
			} catch (InterruptedException e) {
				System.out.println("Not enough carbon to make a frame.");
			}
		}
	}
}
