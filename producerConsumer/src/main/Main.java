package main;

import queue.QueueMain;
import simple.SimpleMain;

public class Main {
    public static void main(String[] args) {
        final int nBuffers = 1000;
        final int nItems = 10000;

//        new SimpleMain(nItems, nBuffers).run();
        new QueueMain(nItems, nBuffers).run();
    }
}
