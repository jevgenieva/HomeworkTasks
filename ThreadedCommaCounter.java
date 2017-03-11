
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadedCommaCounter {
	
	public static AtomicInteger commas = new AtomicInteger();

	public static void main(String[] args) {
		
		//count all commas in a file with a specific number of threads
		
			try {
				System.out.println(threadsCounting(5));
			} catch (IOException | InterruptedException e) 
			{System.out.println("Failed");
			}
	}
	
	static AtomicInteger threadsCounting(int numberOfThreads) throws IOException, InterruptedException {
		
		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
		
		String s = File.separator;
		File file = new File("C:" + s + "Users" + s + "User" + s + "Desktop" + s + "warAndPeace.txt");
		BufferedReader book = new BufferedReader(new FileReader(file));
		
		String text = book.readLine();
		
		//each line of text is submitted in the thread pool as a Runnable
		while(text != null) {
			
			final String temp = text;
			
			pool.submit(new Runnable() {
				
				@Override
				public void run() {
					for (int i = 0; i < temp.length(); i++) {
						if(temp.charAt(i) == ',')
							commas.getAndIncrement();
					}
				}
			});
			
		text = book.readLine();
		
		}
		
		for (Runnable r : pool.getQueue()) {
			r.run();
		}
		
		pool.shutdown();
		book.close();
		
		while(!pool.isTerminated()) {
			//make sure all Runnable tasks are finished
		}
		return commas;
	}
}
