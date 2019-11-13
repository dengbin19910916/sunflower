package io.xxx.sunflower.data;

import io.xxx.sunflower.model.Member;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberReactiveRepository extends ReactiveCrudRepository<Member, Long> {
}
