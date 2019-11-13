package io.xxx.sunflower.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Member {

    @Id
    private Long id;
    private String cardNo;
    private String name;
    private String mobile;
    private Integer gender;
    private LocalDateTime birthday;
    private Long exp;
    private Integer point;
    private String tags;
    private LocalDateTime createdTime;
}
