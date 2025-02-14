package readwrite_lock;

public class Main {
	public static void main(String[] args){
		SecretData secrets = new SecretData();
		
		int numberOfThreads = 10;
		Thread[] threads = new Thread[numberOfThreads];
		
		for (int i = 0; i < numberOfThreads; i++) {
			threads[i] = new Thread(new SecretAgent("agent "+ i, secrets));
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
	}

}
