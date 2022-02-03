package queue;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class Buffer implements CSProcess {
    private final One2OneChannelInt in;
    private final One2OneChannelInt out;

    public Buffer(final One2OneChannelInt in, final One2OneChannelInt out) {
        this.out = out;
        this.in = in;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true)
            out.out().write(in.in().read());
    }
}
