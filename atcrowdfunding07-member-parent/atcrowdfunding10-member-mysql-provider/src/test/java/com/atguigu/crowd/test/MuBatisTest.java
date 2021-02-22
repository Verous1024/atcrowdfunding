package com.atguigu.crowd.test;

import com.atguigu.crowd.entity.po.MemberPO;
import com.atguigu.crowd.mapper.MemberPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-21 上午 11:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MuBatisTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberPOMapper memberPOMapper;

    private Logger logger = LoggerFactory.getLogger(MuBatisTest.class);

    @Test
    public void test() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void test2() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String source = "123123";
        String encoder = passwordEncoder.encode(source);

        MemberPO memberPO = new MemberPO(null, "jack", encoder, "杰克", "jack@qq.com","19821312322", 1, 1, "杰克", "123123", 2);

        memberPOMapper.insert(memberPO);

    }
}
