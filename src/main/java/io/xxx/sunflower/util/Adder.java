package io.xxx.sunflower.util;

import java.util.ArrayList;
import java.util.List;

public class Adder {

    // 主函数
    public static void main(String[] args) {
        int count = 10_000_000;
        List<String> list = new ArrayList<>(count);
        long start = System.currentTimeMillis();
        for (long i = 1; i <= count; i++) {
            list.add(Long.toHexString(i));
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " -- " + list.size());
    }

}
