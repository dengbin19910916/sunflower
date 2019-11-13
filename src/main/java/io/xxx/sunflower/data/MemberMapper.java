package io.xxx.sunflower.data;

import io.xxx.sunflower.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberMapper {

    List<Member> getMembers();
}
