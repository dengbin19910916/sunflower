package io.xxx.sunflower.test.sample;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedComeEventHandler implements EventHandler<FeedComeEvent>, WorkHandler<FeedComeEvent> {

    @Autowired
    private FeedMongoDao feedMongoDao;

    @Override
    public void onEvent(FeedComeEvent event, long sequence, boolean endOfBatch) throws Exception {
        onEvent(event);
    }

    @Override
    public void onEvent(FeedComeEvent event) throws Exception {
        feedMongoDao.log(event);
    }
}
