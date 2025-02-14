package atomics;

import java.util.concurrent.atomic.AtomicInteger;

public class PassengerCounter {

	private AtomicInteger count;
	
	public PassengerCounter(){
		count = new AtomicInteger(0);
	}
	
	public void passengerPassed(){
		count.incrementAndGet();
	}	
	
	public String toString(){
		return "count of passengers is " + count;
	}
}
