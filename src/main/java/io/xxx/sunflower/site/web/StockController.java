package io.xxx.sunflower.site.web;

import io.xxx.sunflower.site.service.StockService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StringRedisTemplate redisTemplate;
    private final StockService stockService;

    public StockController(StringRedisTemplate redisTemplate,
                           StockService stockService) {
        this.redisTemplate = redisTemplate;
        this.stockService = stockService;
    }

    @GetMapping("/decr")
    public void decr() {
        Long value = redisTemplate.opsForValue().decrement("stock");
        if (value != null && value < 0) {
            return;
        }
        stockService.decr(1L);
    }
}
