package io.xxx.sunflower.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CouponPlan {

    private Long id;
    private Long couponTemplateId;
    private Integer totalNum;
    private Integer getNum;
    private LocalDateTime createdTime;
}
