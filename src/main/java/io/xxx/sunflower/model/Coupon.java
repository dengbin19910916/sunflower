package io.xxx.sunflower.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Coupon {

    private Long id;
    private Long couponTemplateId;
    private String number;
    private LocalDateTime createdTime;
}
