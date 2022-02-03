package simple;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannelInt;

public class Consumer implements CSProcess {
    private final One2OneChannelInt[] input;
    private final int howMany;

    public Consumer(One2OneChannelInt[] input, int howMany) {
        this.input = input;
        this.howMany = howMany;
    }

    public void run() {
        long start = System.nanoTime();
        int inputLen = input.length;

        final Guard[] guards = new Guard[inputLen];
        for (int i = 0; i < inputLen; i++)
            guards[i] = input[i].in();

        final Alternative alt = new Alternative(guards);
        for (int i = 0; i < howMany; i++) {
            int index = alt.select();
            input[index].in().read();
        }

        long duration = System.nanoTime() - start;
        System.out.println(duration + "ns");
        System.exit(0);
    }
}