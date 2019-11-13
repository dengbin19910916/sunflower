package io.xxx.sunflower.service.event;

import com.lmax.disruptor.EventFactory;

public class CouponDistributionEventFactory implements EventFactory<CouponDistributionEvent> {

    @Override
    public CouponDistributionEvent newInstance() {
        return null;
    }
}
