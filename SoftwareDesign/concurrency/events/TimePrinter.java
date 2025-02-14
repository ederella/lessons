package events;

import java.util.GregorianCalendar;
import java.util.concurrent.CountDownLatch;

public class TimePrinter implements Runnable{
	
	private CountDownLatch start;

	
	public TimePrinter(CountDownLatch start) {
		this.start = start;

	}

	@Override
	public void run() {
		try {
			start.await();
			System.out.println(new GregorianCalendar().getTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
