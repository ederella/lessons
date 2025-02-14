package synchronized_keyword;

import java.math.BigDecimal;

public class Main {
	
	
	
	public static void main(String[] args) {
		int numberOfThreads = 1000;
		Thread[] threads = new Thread[numberOfThreads];

		Seller seller = new Seller(numberOfThreads, new BigDecimal(numberOfThreads * 10));

		for (int i = 0; i < numberOfThreads; i++) {
			threads[i] = new Thread(new Customer(seller));
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
		System.out.println(seller.getBalance());
		System.out.println(seller.getCount());
	}

}
