package io.xxx.sunflower.data;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StockMapper {

    @Update("update stock set amount = amount - 1 where product_id = #{productId}")
    void decr(@Param("productId") Long productId);
}
