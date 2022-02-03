package simple;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;
import org.jcsp.lang.StandardChannelIntFactory;

/*
 * Implementation with a distributed buffer without respect to order.
 */
public class SimpleMain implements Runnable {
    private final int nItems;
    private final int nBuffers;

    public SimpleMain(int nItems, int nBuffers) {
        this.nItems = nItems;
        this.nBuffers = nBuffers;
    }

    public void run() {
        System.out.println("SimpleMain");
        StandardChannelIntFactory channelIntFactory = new StandardChannelIntFactory();
        final One2OneChannelInt[] channelsProducer = channelIntFactory.createOne2One(nBuffers);
        final One2OneChannelInt[] channelsBuffer = channelIntFactory.createOne2One(nBuffers);
        final One2OneChannelInt[] channelsConsumer = channelIntFactory.createOne2One(nBuffers);

        CSProcess[] processesList = new CSProcess[nBuffers + 2];
        processesList[0] = new Producer(channelsProducer, channelsBuffer, nItems);
        processesList[1] = new Consumer(channelsConsumer, nItems);

        for (int i = 0; i < nBuffers; i++)
            processesList[i + 2] = new Buffer(
                    channelsProducer[i],
                    channelsConsumer[i],
                    channelsBuffer[i]);

        Parallel parallel = new Parallel(processesList);
        parallel.run();
    }
}