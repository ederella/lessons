package futures;

import java.net.URISyntaxException;

public class Main {
	public static void main(String[] args) throws URISyntaxException {

		int numberOfThreads = 5;
		Thread[] threads = new Thread[numberOfThreads];
		threads[0] = new Thread(new WeatherPrinter("Moscow"));
		threads[1] = new Thread(new WeatherPrinter("London"));
		threads[2] = new Thread(new WeatherPrinter("Riga"));
		threads[3] = new Thread(new WeatherPrinter("Paris"));
		threads[4] = new Thread(new WeatherPrinter("Berlin"));
		
		for(int i = 0; i < numberOfThreads ; i++) {
			threads[i].start();
		}
		
		for (int i = 0; i < numberOfThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
