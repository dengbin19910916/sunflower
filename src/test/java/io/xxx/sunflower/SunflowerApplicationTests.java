package io.xxx.sunflower;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class SunflowerApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
        addMembers(500_000);
        addCouponTemplate(1);
        addCouponPlan(1);
    }

    private void addMembers(int count) {
        for (int i = 1; i <= count; i++) {
            jdbcTemplate.execute("insert into member(card_no, name) " +
                    "values ('" + String.format("%012d", i) + "', '姓名" + i + "')");
        }
    }

    private void addCouponTemplate(int count) {
        for (int i = 1; i <= count; i++) {
            jdbcTemplate.execute("insert into coupon_template(name, code) " +
                    "values ('券模板" + i + "', '100" + i + "')");
        }
    }

    private void addCouponPlan(int count) {
        for (int i = 1; i <= count; i++) {
            jdbcTemplate.execute("insert into coupon_plan(coupon_template_id, total_num) " +
                    "values (" + i + ", 100000)");
        }
    }
}
