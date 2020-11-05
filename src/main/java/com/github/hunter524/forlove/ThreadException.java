package com.github.hunter524.forlove;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadException {
    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("uncaughtException");
                e.printStackTrace();
            }
        });
        Runnable child_thread_run = new Runnable() {
            @Override
            public void run() {
                System.out.println("child Thread Run");
                throw new IllegalStateException();
            }
        };
//        new Thread(child_thread_run).start();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(child_thread_run);
        while (true) {
            Thread.sleep(1000);
            System.out.println("Current Time is:" + System.currentTimeMillis());
        }
    }
}
