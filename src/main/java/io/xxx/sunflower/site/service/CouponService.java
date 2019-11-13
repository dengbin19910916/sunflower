package io.xxx.sunflower.site.service;

import io.xxx.sunflower.data.MemberMapper;
import io.xxx.sunflower.model.CouponPlan;
import io.xxx.sunflower.model.CouponTemplate;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CouponService {

    private final MemberMapper memberMapper;
    private final JdbcTemplate jdbcTemplate;
    private final SqlSessionTemplate sqlSessionTemplate;

    public CouponService(MemberMapper memberMapper,
                         JdbcTemplate jdbcTemplate,
                         SqlSessionTemplate sqlSessionTemplate) {
        this.memberMapper = memberMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    /**
     * 发券。
     *
     * @param planId 发券方案ID
     */
    public void distributeCoupons(Long planId) throws SQLException, InterruptedException {
//        Optional<CouponPlan> couponPlan = getCouponPlan(planId);
//        if (couponPlan.isEmpty()) {
//            return;
//        }
//
//        Optional<CouponTemplate> couponTemplate = getCouponTemplate(couponPlan.get());
//        if (couponTemplate.isEmpty()) {
//            return;
//        }

//        sqlSessionTemplate.select("io.xxx.sunflower.data.MemberMapper.getMembers", resultContext -> {
//            System.out.println(resultContext.getResultObject());
//        });
//        List<Member> members = memberMapper.getMembers();
//        System.out.println(members.size());

        AtomicInteger i = new AtomicInteger(0);
        Connection connection = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
        connection.setAutoCommit(false);
        String sql = "select * from member";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setFetchSize(100_000);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getLong("id"));
            i.getAndIncrement();
        }
        System.err.println(i);

//        Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection().setAutoCommit(false);
//        jdbcTemplate.setFetchSize(1);
//        jdbcTemplate.query("select * from member", rs -> {
//            System.out.println(rs.getLong("id"));
//        });

//        BlockingQueue<Coupon> couponQueue = new LinkedBlockingQueue<>();
//
//        for (int i = 0; i < couponPlan.get().getTotalNum(); i++) {
//            String couponNo = couponTemplate.get().getCode() + Long.toHexString(i).toUpperCase();
////            jdbcTemplate.execute()
//        }
    }

    private Optional<CouponPlan> getCouponPlan(Long planId) {
        return Optional.ofNullable(jdbcTemplate.query(
                "select id, coupon_template_id, total_num, get_num " +
                        "from coupon_plan where id = ?", rs -> {
                    if (rs.next()) {
                        CouponPlan cp = new CouponPlan();
                        cp.setId(rs.getLong(1));
                        cp.setCouponTemplateId(rs.getLong(2));
                        cp.setTotalNum(rs.getInt(3));
                        cp.setGetNum(rs.getInt(4));
                        return cp;
                    }
                    return null;
                }, planId));
    }

    private Optional<CouponTemplate> getCouponTemplate(CouponPlan couponPlan) {
        return Optional.ofNullable(jdbcTemplate.query(
                "select id, name, code from coupon_template where id = ?",
                rs -> {
                    if (rs.next()) {
                        CouponTemplate ct = new CouponTemplate();
                        ct.setId(rs.getLong(1));
                        ct.setName(rs.getString(2));
                        ct.setCode(rs.getString(3));
                        return ct;
                    }
                    return null;
                }, couponPlan.getCouponTemplateId()));
    }
}
