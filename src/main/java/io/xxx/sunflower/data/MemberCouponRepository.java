package io.xxx.sunflower.data;

import io.xxx.sunflower.model.MemberCoupon;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCouponRepository extends ReactiveCrudRepository<MemberCoupon, Long> {
}
