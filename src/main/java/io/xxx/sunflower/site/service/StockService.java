package io.xxx.sunflower.site.service;

import io.xxx.sunflower.data.StockMapper;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final StockMapper stockMapper;

    public StockService(StockMapper stockMapper) {
        this.stockMapper = stockMapper;
    }

    public void decr(Long productId) {
        stockMapper.decr(productId);
    }
}
