package io.xxx.sunflower.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueTest {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                try {
                    queue.put(i);
                } catch (InterruptedException ignored) {
                }
            }
        });

        Thread consumer1 = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("C1: " + queue.take());
                }
            } catch (InterruptedException ignored) {
            }
        });

        Thread consumer2 = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("C2: " + queue.take());
                }
            } catch (InterruptedException ignored) {
            }
        });


        producer.start();
        consumer1.start();
        consumer2.start();
    }
}