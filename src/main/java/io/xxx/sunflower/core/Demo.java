package io.xxx.sunflower.core;

import java.util.stream.IntStream;

public class Demo {

    public static void main(String[] args) {
        try (CouponDistributePublisher<Integer> publisher = new CouponDistributePublisher<>()) {
            demoSubscribe(publisher, "One");
            demoSubscribe(publisher, "Two");
            demoSubscribe(publisher, "Three");

            IntStream.range(1, 5)
                    .forEach(publisher::submit);
        }
    }

    private static void demoSubscribe(CouponDistributePublisher<Integer> publisher, String subscriberName) {
        CouponDistributeSubscriber<Integer> subscriber = new CouponDistributeSubscriber<>(subscriberName, 1);
        publisher.subscribe(subscriber);
    }
}
