package io.xxx.sunflower.test.trade;

import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.EventHandler;

import java.util.concurrent.TimeUnit;

public class OrderEventHandler implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("保存订单: " + JSON.toJSONString(event));
        TimeUnit.SECONDS.sleep(1);
    }
}
