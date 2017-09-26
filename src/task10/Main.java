package task10;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by admin on 25.09.2017.
 */
public class Main {
    public static void main(String[] args) {

        BlockingQueue queue = new ArrayBlockingQueue<>(1);
        Integer counter = 0;

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue, counter));

        producer.start();
        consumer.start();

        if (producer.isAlive())
            try {
                producer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        if (consumer.isAlive())
            try {
                consumer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        System.out.println("Кол-во найденных слов: "+counter);
    }
}
