package org.example.ex3;

import static org.example.ex3.BatchCreation.CAPACITY;

public class MyOtusRunnable implements Runnable {

    private static long accept(long[] value) {
        return value[0] * value[0];
    }

    @Override
    public void run() {
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

    }
}
