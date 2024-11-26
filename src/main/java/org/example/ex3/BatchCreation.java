package org.example.ex3;

import java.util.concurrent.ThreadFactory;

public class BatchCreation {

    //    public static final int THEAD_COUNT = 10; // lesson 1
    public static final int THEAD_COUNT = 1_000_000; // lesson 2

    public static final int CAPACITY = 1;

    public static void main(String[] args) throws InterruptedException {
//        startPlatform();
        startVirtual();
    }

    private static void startPlatform() throws InterruptedException {
        Thread.Builder builder = Thread.ofPlatform();

        startThreads(builder);
    }

    private static void startVirtual() throws InterruptedException {
        Thread.Builder builder = Thread.ofVirtual();

        startThreads(builder);
    }

    private static void startThreads(Thread.Builder builder) throws InterruptedException {
        ThreadFactory factory = builder.factory();

        Runnable waitTask = () -> {
//            try {
            long[] value = new long[CAPACITY];
            while (true) {
                for (int i = 0; i < 10_000; i++) {
                    value[0] += i * i;
                }
//                    Thread.sleep(1);
                accept(value);
            }
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        };

        Thread last = null;
        for (int i = 0; i < THEAD_COUNT; i++) {
            last = factory.newThread(waitTask);
            last.start();
            if (i % 1000 == 0) {
                System.out.println("Thread " + last.threadId() + "  started (virtual=" + last.isVirtual() + " )");
            }
        }

        if (builder instanceof Thread.Builder.OfVirtual) {
            last.join();
        }
    }

    private static long accept(long[] value) {
        return value[0] * value[0];
    }
}
