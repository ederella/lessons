package atomics;

public class Passenger implements Runnable{
	private PassengerCounter pc;
	
	public Passenger(PassengerCounter pc){
		this.pc = pc;
	}

	public void run() {
		pc.passengerPassed();
	}

}
