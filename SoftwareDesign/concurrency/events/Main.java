package events;

import java.util.concurrent.CountDownLatch;

public class Main {

	public static void main(String[] args) {
		CountDownLatch start = new CountDownLatch(1);
		
		new Thread(new TimePrinter(start)).start();

		System.out.println("Current time is ");
		start.countDown();		
	}

}
