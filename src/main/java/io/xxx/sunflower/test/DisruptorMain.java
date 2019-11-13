package io.xxx.sunflower.test;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

public class DisruptorMain {

    public static void main(String[] args) {
        //事件生产工厂：
        LongEventFactory longEventFactory = new LongEventFactory();
        //RingBuffer的大小：
        int bufferSize = 1024 * 1024;
        //实例化disruptor对象：初始化RingBuffer
        Disruptor<LongEvent> disruptor = new Disruptor<>(longEventFactory, bufferSize,
                Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());
        //设置事件的执行者：(单消费者)
        disruptor.handleEventsWith(new LongEventHandler());
        //disruptor启动：
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        //设置事件单生产者：
        for (long x = 0; x < 1_000_000; x++) {
            // 获取下一个可用位置的下标
            long sequence = ringBuffer.next();
            try {
                // 返回可用位置的元素
                LongEvent event = ringBuffer.get(sequence);
                // 设置该位置元素的值
                event.setValue(x);
            } finally {
                //发布事件
                ringBuffer.publish(sequence);
            }
        }
        disruptor.shutdown();
    }
}
