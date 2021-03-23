package com.learn.niu.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void set() {
       /* redisTemplate.opsForValue().set("studyJava", "come");
        Object x = redisTemplate.opsForValue().get("studyJava");
        System.out.println(x);*/

        redisTemplate.opsForValue().setBit("infoids", 123456L, true);
        redisTemplate.delete("infoids");
        Boolean falg = redisTemplate.opsForValue().getBit("infoids", 123456L);
        redisTemplate.opsForValue().setBit("infoids", 123456L, false);
         falg = redisTemplate.opsForValue().getBit("infoids", 123456L);

        boolean flags = redisTemplate.opsForValue().getBit("infoids", 123456888L);
        System.out.println(falg);

    }
}
