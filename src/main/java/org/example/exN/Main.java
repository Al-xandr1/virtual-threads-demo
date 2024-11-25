package org.example.exN;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.lang.Thread.sleep;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {

        final ThreadFactory factory = Thread.ofVirtual().name("routine-", 0).factory();
        try (var executor = Executors.newThreadPerTaskExecutor(factory)) {
            var bathTime =
                    executor.submit(
                            () -> {
                                log.info("I'm going to take a bath");
                                try {
                                    sleep(Duration.ofMillis(500L));
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                log.info("I'm done with the bath");
                            });
            var boilingWater =
                    executor.submit(
                            () -> {
                                log.info("I'm going to boil some water");
                                try {
                                    sleep(Duration.ofSeconds(1L));
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                log.info("I'm done with the water");
                            });
            bathTime.get();
            boilingWater.get();
        }

//        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
//            executorService.execute(() -> System.out.println("Hello from virtual thread: " + Thread.currentThread().isVirtual()));
//        }
//        ThreadFactory factory = Thread.ofVirtual().factory();
//        Thread thread = factory.newThread(() -> System.out.println(STR."Virtual thread from threadFactory = \{Thread.currentThread().isVirtual()}"));
//        thread.start();
//        thread.join();
    }
}