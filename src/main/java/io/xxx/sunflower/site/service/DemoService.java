package io.xxx.sunflower.site.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class DemoService {

    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public DemoService(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    public void execute() {
        doService1();
//        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//            @Override
//            protected void doInTransactionWithoutResult(@Nonnull TransactionStatus status) {
//                try {
//                    doService1();
//                } catch (Exception e) {
//                    System.out.println("进入异常");
//                    status.setRollbackOnly();
////                    throw e;
//                }
//            }
//        });
    }

    @Transactional
    public void doService1() {
        jdbcTemplate.update("insert into payment_method(name) values ('do service1')");

        try {
            doService2();
        } catch (Exception ignored) {

        }
    }

    @Transactional
    public Integer doService2() {
        jdbcTemplate.update("insert into payment_method(name) values ('do service2')");
        return 100 / 0;
    }
}
