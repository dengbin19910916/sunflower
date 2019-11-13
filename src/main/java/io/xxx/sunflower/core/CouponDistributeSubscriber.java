package io.xxx.sunflower.core;

import lombok.Getter;

import java.util.concurrent.Flow;

public class CouponDistributeSubscriber<T> implements Flow.Subscriber<T> {

    @Getter
    private String name;
    @Getter
    private Flow.Subscription subscription;
    private final long bufferSize;

    public CouponDistributeSubscriber(String name, long bufferSize) {
        this.name = name;
        this.bufferSize = bufferSize;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        (this.subscription = subscription).request(bufferSize);
    }

    @Override
    public void onNext(T item) {
        System.out.println(item);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
