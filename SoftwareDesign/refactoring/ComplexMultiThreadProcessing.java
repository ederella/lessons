package refactoring;

import java.util.Arrays;
import java.util.Random;

public class ComplexMultiThreadProcessing {
	private static final int SIZE = 1_000_000;
	private static final int THREADS = 4;
	private static final int[] data = new int[SIZE];
	private static volatile int sum = 0;

	public static void main(String[] args) {
		Random random = new Random();
		Arrays.setAll(data, i -> random.nextInt(100));

		Thread[] threads = new Thread[THREADS];
		int chunkSize = SIZE / THREADS;

		Arrays.setAll(threads, i -> {
			final int start = i * chunkSize;
			final int end = (i + 1) * chunkSize;
			return new Thread(() -> calcSum(Arrays.stream(data, start, end).sum()));
		});

		Arrays.stream(threads).forEach(t -> t.start());
		Arrays.stream(threads).forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println("Sum of all elements: " + sum);
	}

	private static synchronized void calcSum(int localSum) {
		sum += localSum;
	}
}