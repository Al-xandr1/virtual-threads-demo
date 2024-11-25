package org.example.ex1;

import static java.text.MessageFormat.format;

public class SimpleCreation {

    public static void main(String[] args) throws InterruptedException {
        sample1();
        sample2();
        sample3();
    }

    private static void sample1() throws InterruptedException {
        Thread.Builder.OfVirtual ofVirtual = Thread.ofVirtual();
        Thread thread = ofVirtual.start(
                () -> System.out.println(format("S1: Hello from virtual thread! IsVirtual={0}", Thread.currentThread().isVirtual()))
        );
        thread.join();
    }

    private static void sample2() throws InterruptedException {
        Thread.Builder.OfVirtual ofVirtual = Thread.ofVirtual();
        ofVirtual.name("otus-virtual-thread");
        Thread thread = ofVirtual.unstarted(
                () -> System.out.println(format("S2: Hello from virtual thread! IsVirtual={0}, name={1}",
                        Thread.currentThread().isVirtual(),
                        Thread.currentThread().getName()))
        );
        thread.start();
        thread.join();
    }

    private static void sample3() throws InterruptedException {
        Thread.Builder.OfPlatform ofPlatform = Thread.ofPlatform();
        ofPlatform.name("otus-platform-thread");
        Thread thread = ofPlatform.start(
                () -> System.out.println(format("S2: Hello from platform thread! IsVirtual={0}, name={1}",
                        Thread.currentThread().isVirtual(),
                        Thread.currentThread().getName()))
        );
        thread.join();
    }
}
