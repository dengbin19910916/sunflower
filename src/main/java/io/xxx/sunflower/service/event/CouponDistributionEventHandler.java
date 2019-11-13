package io.xxx.sunflower.service.event;

import com.lmax.disruptor.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class CouponDistributionEventHandler implements EventHandler<CouponDistributionEvent> {

    @Override
    public void onEvent(CouponDistributionEvent event, long sequence, boolean endOfBatch)
            throws Exception {

    }
}
