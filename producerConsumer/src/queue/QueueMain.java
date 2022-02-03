package queue;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;
import org.jcsp.lang.StandardChannelIntFactory;

/*
* Implementation with a distributed buffer with respect to order.
 */
public class QueueMain implements Runnable {
    private final int nItems;
    private final int nBuffers;

    public QueueMain(int nItems, int nBuffers) {
        this.nItems = nItems;
        this.nBuffers = nBuffers;
    }

    public void run() {
        System.out.println("QueueMain");
        StandardChannelIntFactory fact = new StandardChannelIntFactory();
        final One2OneChannelInt[] channels = fact.createOne2One(nBuffers + 1);

        CSProcess[] procList = new CSProcess[nBuffers + 2];
        procList[0] = new Producer(channels[0], nItems);
        procList[1] = new Consumer(channels[nBuffers], nItems);

        for (int i = 0; i < nBuffers; i++)
            procList[i + 2] = new Buffer(channels[i], channels[i + 1]);

        Parallel par = new Parallel(procList);
        par.run();
    }
}
