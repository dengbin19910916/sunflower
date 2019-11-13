package io.xxx.sunflower.test;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

class DataItem {
    private final int number;
    private final boolean flag;

    static final AtomicInteger count = new AtomicInteger(0);

    DataItem(int number) {
        this.number = number;
        flag = (number % 3) == 0;
        count.incrementAndGet();
    }

    void show() {
        System.out.println(number + ": " + ((flag) ? "Urgency" : "Common"));
    }

    boolean isUrgency() {
        return flag;
    }
}

class Receiver extends Thread {
    private final BlockingDeque<DataItem> queue;

    Receiver(BlockingDeque<DataItem> queue) {
        this.queue = queue;
    }

    public void run() {
        DataItem item;
        try {
            while (true) {
                item = queue.takeLast();
                item.show();
                DataItem.count.decrementAndGet();
            }
        } catch (InterruptedException ie) {
            System.out.println("Receiver finished");
        }
    }
}

public class BlockingDequeDemo {

    public static void main(String[] args) {
        BlockingDeque<DataItem> queue = new LinkedBlockingDeque<>(20);

        try {
            for (int i = 0; i < 10; i++) {
                DataItem item = new DataItem(i);
                if (item.isUrgency()) {
                    queue.putLast(item);
                } else {
                    queue.putFirst(item);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        Thread receiver = new Thread(new Receiver(queue));
        receiver.start();

        while (DataItem.count.get() > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        receiver.interrupt();

        System.out.println("Main finished");
    }
}
