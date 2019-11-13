package io.xxx.sunflower.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class MemberCoupon {

    @Id
    private Long id;
    private Long memberId;
    private String couponNo;
    private Date createdTime;
}
