package simple;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannelInt;

public class Producer implements CSProcess {
    private final One2OneChannelInt[] output;
    private final One2OneChannelInt[] buffer;
    private final int size;

    public Producer(final One2OneChannelInt[] output,
                    final One2OneChannelInt[] buffer,
                    final int size) {
        this.output = output;
        this.buffer = buffer;
        this.size = size;
    }

    public void run() {
        final Guard[] guards = new Guard[buffer.length];

        for (int i = 0; i < output.length; i++)
            guards[i] = buffer[i].in();

        final Alternative alt = new Alternative(guards);
        for (int i = 0; i < size; i++) {
            int index = alt.select();
            buffer[index].in().read();
            int item = (int) (Math.random() * 100) + 1;
            output[index].out().write(item);
        }
    }
}
