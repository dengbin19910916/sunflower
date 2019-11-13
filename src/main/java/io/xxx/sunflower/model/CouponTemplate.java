package io.xxx.sunflower.model;

import lombok.Data;

import java.util.Date;

@Data
public class CouponTemplate {

    private Long id;
    private String name;
    private String code;
    private Date createdTime;
}
