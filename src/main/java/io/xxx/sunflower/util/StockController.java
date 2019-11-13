package io.xxx.sunflower.util;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StringRedisTemplate redisTemplate;

    public StockController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/decr")
    public ResponseEntity<Optional<String>> decr() {
        Long value = redisTemplate.opsForValue().decrement("stock");
        return value != null && value < 0
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.OK).build();
    }
}
