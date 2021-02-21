package com.atguigu.crowd.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-21 下午 02:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

        operations.set("apple", "red");
    }
}
