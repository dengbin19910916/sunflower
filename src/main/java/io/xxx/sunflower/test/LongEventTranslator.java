package io.xxx.sunflower.test;

import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.EventTranslatorOneArg;

public class LongEventTranslator implements EventTranslatorOneArg<LongEvent, Long> {

    @Override
    public void translateTo(LongEvent event, long sequence, Long arg0) {
        System.out.println("translate to sequence: " + sequence
                + ", arg0: " + arg0 + ", " + JSON.toJSONString(event));
        event.setValue(arg0);
    }
}
