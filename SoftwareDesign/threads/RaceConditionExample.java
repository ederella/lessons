package threads;

public class RaceConditionExample {

    private static int counter = 0;

    public static void main(String[] args) {
        int numberOfThreads = 10;
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    counter++;
                }
            });
          
        }

        for (int i = 0; i < numberOfThreads; i++) {
            try {
            	threads[i].start();
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final counter value: " + counter);
        //потоки стартуют быстрее, чем увеличивается счетчик, 
        //поэтому каждый новый поток получает переменную counter до того, как предыдущие потоки закончили ее увеличение,
        //таким образом counter может несколько раз перезаписываться одним и тем же занчением
    }
}
