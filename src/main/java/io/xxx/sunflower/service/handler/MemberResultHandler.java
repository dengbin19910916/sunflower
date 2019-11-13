package io.xxx.sunflower.service.handler;

import io.xxx.sunflower.model.Member;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

public class MemberResultHandler implements ResultHandler<Member> {

    @Override
    public void handleResult(ResultContext<? extends Member> resultContext) {

    }
}
