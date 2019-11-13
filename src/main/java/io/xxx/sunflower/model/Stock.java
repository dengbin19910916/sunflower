package io.xxx.sunflower.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Stock {

    @Id
    private Long id;
    private Long warehouseId;
    private Long productId;
    private Integer amount;
    private LocalDateTime createdTime;
}
