package io.xxx.sunflower.service.event;

import lombok.Data;

@Data
public class CouponDistributionEvent {

    private String couponNo;
    private Long memberId;
    private int amount;
}
