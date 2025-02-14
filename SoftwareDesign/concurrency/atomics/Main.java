package atomics;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		PassengerCounter counter = new PassengerCounter();

		int numberOfThreads = new Random().nextInt(10000);
		
		System.out.println("number of threads is "+ numberOfThreads);
		Thread[] threads = new Thread[numberOfThreads];

		for (int i = 0; i < numberOfThreads; i++) {
			threads[i] = new Thread(new Passenger(counter));
		}
		for (int i = 0; i < numberOfThreads; i++) {
			threads[i].start();
		}

		for (int i = 0; i < numberOfThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(counter.toString());
	}
}
