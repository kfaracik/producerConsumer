package queue;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class Consumer implements CSProcess {
    private final One2OneChannelInt in;
    private final int howMany;

    public Consumer(final One2OneChannelInt in, final int howMany) {
        this.in = in;
        this.howMany = howMany;
    }

    public void run() {
        long start = System.nanoTime();
        for (int i = 0; i < howMany; i++) {
            in.in().read();
        }

        long duration = System.nanoTime() - start;
        System.out.println(duration + "ns");
        System.exit(0);
    }
}