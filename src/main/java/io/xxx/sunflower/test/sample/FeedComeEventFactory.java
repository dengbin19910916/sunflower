package io.xxx.sunflower.test.sample;

import com.lmax.disruptor.EventFactory;

public class FeedComeEventFactory implements EventFactory<FeedComeEvent> {

    @Override
    public FeedComeEvent newInstance() {
        return new FeedComeEvent();
    }
}
