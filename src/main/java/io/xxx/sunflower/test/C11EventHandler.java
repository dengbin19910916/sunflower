package io.xxx.sunflower.test;

import com.lmax.disruptor.EventHandler;

public class C11EventHandler implements EventHandler<LongEvent>
//        , WorkHandler<LongEvent>
{

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        long value = event.getValue();
        value += 10;
        System.out.println(System.currentTimeMillis()
                + ": c1-1 consumer finished.number=" + value
                + " >> " + Thread.currentThread().getName());
//        try {
//            TimeUnit.MILLISECONDS.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

//    @Override
//    public void onEvent(LongEvent event) {
//        long value = event.getValue();
//        value += 10;
//        System.out.println(System.currentTimeMillis() + ": c1-1 consumer finished.number: " + value);
//    }
}
