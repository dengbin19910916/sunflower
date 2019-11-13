package io.xxx.sunflower.core;

import java.util.List;
import java.util.concurrent.*;

public class CouponDistributePublisher<T> implements Flow.Publisher<T>, AutoCloseable {

    private final ExecutorService executor = ForkJoinPool.commonPool();
    private List<CouponDistributeSubscription<T>> subscriptions = new CopyOnWriteArrayList<>();

    public void submit(T item) {
        subscriptions.forEach(e -> e.future = executor.submit(() -> e.subscriber.onNext(item)));
    }

    @Override
    public void close() {
        subscriptions.forEach(e -> e.future = executor.submit(e.subscriber::onComplete));
    }

    @Override
    public void subscribe(Flow.Subscriber<? super T> subscriber) {
        subscriber.onSubscribe(new CouponDistributeSubscription<>(subscriber, executor));
        subscriptions.add(new CouponDistributeSubscription<>(subscriber, executor));
    }

    static class CouponDistributeSubscription<T> implements Flow.Subscription {

        private final Flow.Subscriber<? super T> subscriber;
        private final ExecutorService executor;
        private Future<?> future;
        private T item;
        private boolean completed;

        CouponDistributeSubscription(Flow.Subscriber<? super T> subscriber, ExecutorService executor) {
            this.subscriber = subscriber;
            this.executor = executor;
        }

        @Override
        public void request(long n) {
            if (n != 0 && !completed) {
                if (n < 0) {
                    executor.execute(() -> subscriber.onError(new IllegalArgumentException()));
                } else {
                    future = executor.submit(() -> subscriber.onNext(item));
                }
            } else {
                executor.shutdown();
                subscriber.onComplete();
            }
        }

        @Override
        public void cancel() {
            completed = true;
            if (future != null && !future.isCancelled()) {
                future.cancel(true);
            }
        }
    }
}
