package io.xxx.sunflower.test;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int bufferSize = 512;   // 环形队列长度，必须是2的N次方
        EventFactory<LongEvent> eventFactory = new LongEventFactory();

         // 定义Disruptor，基于单生产者，阻塞策略
        Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, bufferSize,
                Executors.defaultThreadFactory(), ProducerType.SINGLE, new YieldingWaitStrategy());
        /////////////////////////////////////////////////////////////////////
//        parallel(disruptor);
//        serial(disruptor);
        diamond(disruptor);
//        chain(disruptor);
        /////////////////////////////////////////////////////////////////////
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        // 发送事件
//        for (long i = 1; i <= 10_000; i++) {
//            ringBuffer.publishEvent(new LongEventTranslator(), i);
//        }
//        ringBuffer.publishEvent(new LongEventTranslator(), 10L);
//        ringBuffer.publishEvent(new LongEventTranslator(), 100L);
        for (int i = 1; i <= 1_000_000; i++) {
            ringBuffer.publishEvent(new LongEventTranslator(), Long.valueOf(i));
            System.out.println("Producer: " + i);
        }

        disruptor.shutdown();
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

    /**
     * <pre>
     * 并行计算实现,c1,c2互相不依赖
     *
     * p --> c11
     *   --> c21
     * </pre>
     */
    private static void parallel(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWith(new C11EventHandler(), new C21EventHandler());
    }

    /**
     * 串行依次执行
     * <br/>
     * p --> c11 --> c21
     */
    private static void serial(Disruptor<LongEvent> disruptor) {
//        disruptor.handleEventsWith(new C11EventHandler()).then(new C21EventHandler());
        disruptor.handleEventsWith(new C11EventHandler());
    }

    /**
     * <pre>
     * 菱形方式执行
     *
     *   --> c11
     * p          --> c21
     *   --> c12
     * </pre>
     */
    private static void diamond(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWith(new C11EventHandler(), new C12EventHandler())
                .then(new C21EventHandler());
    }

    /**
     * <pre>
     * 链式并行计算
     *
     *   --> c11 --> c12
     * p
     *   --> c21 --> c22
     * </pre>
     */
    private static void chain(Disruptor<LongEvent> disruptor) {
        disruptor.handleEventsWith(new C11EventHandler()).then(new C12EventHandler());
        disruptor.handleEventsWith(new C21EventHandler()).then(new C22EventHandler());
    }
}
