package io.xxx.sunflower.test;

import com.lmax.disruptor.EventHandler;

public class C12EventHandler implements EventHandler<LongEvent>
//        , WorkHandler<LongEvent>
{

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        long number = event.getValue();
        number *= 10;
        System.out.println(System.currentTimeMillis()
                + ": c1-2 consumer finished.number=" + number
                + " >> " + Thread.currentThread().getName());
    }

//    @Override
//    public void onEvent(LongEvent event) {
//        long number = event.getValue();
//        number *= 10;
//        System.out.println(System.currentTimeMillis()+": c1-2 consumer finished.number: " + number);
//    }
}
