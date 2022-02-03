package simple;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class Buffer implements CSProcess {
    private final One2OneChannelInt input;
    private final One2OneChannelInt output;
    private final One2OneChannelInt buffer;

    public Buffer(final One2OneChannelInt input,
                  final One2OneChannelInt output,
                  final One2OneChannelInt buffer) {
        this.output = output;
        this.input = input;
        this.buffer = buffer;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true) {
            buffer.out().write(0);
            output.out().write(input.in().read());
        }
    }
}
