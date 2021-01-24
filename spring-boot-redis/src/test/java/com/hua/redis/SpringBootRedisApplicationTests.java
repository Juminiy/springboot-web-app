package com.hua.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringBootRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate ;
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("mykey","value");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
        System.out.println(redisTemplate.opsForValue().get("kps"));
    }

}
