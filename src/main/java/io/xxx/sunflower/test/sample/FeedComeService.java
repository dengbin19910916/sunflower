package io.xxx.sunflower.test.sample;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

@Service
public class FeedComeService implements DisposableBean, InitializingBean {

    private Disruptor<FeedComeEvent> disruptor;
    private static final int RING_BUFFER_SIZE = 8;

    private final FeedComeEventHandler feedComeEventHandler;

    public FeedComeService(FeedComeEventHandler feedComeEventHandler) {
        this.feedComeEventHandler = feedComeEventHandler;
    }

    @Override
    public void destroy() {
        disruptor.shutdown();
    }

    @Override
    public void afterPropertiesSet() {
        disruptor = new Disruptor<>(new FeedComeEventFactory(), RING_BUFFER_SIZE, Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.setDefaultExceptionHandler(new FeedComeEventHandlerException());
        disruptor.handleEventsWith(feedComeEventHandler);
        disruptor.start();
    }

    public void feedCome(int consumerId) {
        RingBuffer<FeedComeEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent((event, sequence, arg0) ->
                event.setConsumerId(arg0), consumerId);

    }
}
