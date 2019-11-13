package io.xxx.sunflower.service;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import io.xxx.sunflower.service.event.CouponDistributionEvent;
import io.xxx.sunflower.service.event.CouponDistributionEventFactory;
import io.xxx.sunflower.service.event.CouponDistributionEventHandler;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

@Service
public class CouponDistributionService implements InitializingBean, DisposableBean {

    private static final int BUFFER_SIZE = 8;
    private Disruptor<CouponDistributionEvent> disruptor;

    private final CouponDistributionEventHandler handler;

    public CouponDistributionService(CouponDistributionEventHandler handler) {
        this.handler = handler;
    }

    @Override
    public void destroy() {
        if (disruptor != null) {
            disruptor.shutdown();
        }
    }

    @Override
    public void afterPropertiesSet() {
        disruptor = new Disruptor<>(new CouponDistributionEventFactory(), BUFFER_SIZE,
                Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(handler);
        disruptor.start();
    }
}
