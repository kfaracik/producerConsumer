package queue;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class Producer implements CSProcess {
    private final One2OneChannelInt output;
    private final int size;

    public Producer(final One2OneChannelInt out, final int howMany) {
        this.output = out;
        this.size = howMany;
    }

    public void run() {
        for (int i = 0; i < size; i++) {
            int item = (int) (Math.random() * 100) + 1;
            output.out().write(item);
        }
    }
}