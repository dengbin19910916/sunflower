package io.xxx.sunflower.test.sample;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class FeedMongoDao {

    void log(FeedComeEvent event) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.println("DAO: " + JSON.toJSONString(event));
    }
}
