package task10;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

import static sun.plugin2.os.windows.Windows.ReadFile;

/**
 * Created by admin on 25.09.2017.
 */
public class Producer implements Runnable{
    private BlockingQueue queue;

    public Producer(BlockingQueue queue) {
       this.queue = queue;
    }

    @Override
    public void run() {
//      String fileName = 'war.txt';
        Path path = Paths.get("111.txt");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> queue.add(s));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
