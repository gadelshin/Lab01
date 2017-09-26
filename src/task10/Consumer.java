package task10;

import java.util.concurrent.BlockingQueue;

/**
 * Created by admin on 25.09.2017.
 */
public class Consumer implements Runnable{
    private BlockingQueue queue;
    private Integer counter;

    public Consumer(BlockingQueue queue, Integer counter) {
        this.queue = queue;
        this.counter = counter;
    }

//    public Consumer() {
//    }

    @Override
    public void run() {
        String s;
        while (true)
            while (queue.remainingCapacity() > 0) {
                try {
                    s = (String) queue.poll();
//            if (s.contains("страдание")) {
                    if (s.contains("я")) {
                        counter++;
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
    }
}

