package io.xxx.sunflower.site.web;

import io.xxx.sunflower.site.service.CouponService;
import lombok.SneakyThrows;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    private final CuratorFramework client;
    private final CouponService couponService;

    private final static int RUNNING = 0;
    private final static int COMPLETED = 1;

    public CouponController(CuratorFramework client,
                            CouponService couponService) {
        this.client = client;
        this.couponService = couponService;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
    }

    @SneakyThrows
    @PostMapping("/distribute")
    public void distribute(@RequestParam Long planId) {
        couponService.distributeCoupons(planId);
//        InterProcessLock lock = new InterProcessMutex(client, "/lock/coupon/distribute/" + planId);
//        if (lock.acquire(0, TimeUnit.SECONDS)) {
//            try {
//                couponService.distributeCoupons(planId);
//                return COMPLETED;
//            } finally {
//                lock.release();
//            }
//        }
//        return RUNNING;
    }
}
