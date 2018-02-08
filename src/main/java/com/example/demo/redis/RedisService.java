package com.example.demo.redis;/*
package artec.base.redis;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisService {

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOperations;

    public Long getVisitCount(){

        Long count = 0L;

        try {
            valueOperations.increment("visitCount", 1);
            count = Long.valueOf(valueOperations.get("visitCount").toString());
        }

        catch (Exception e){

            System.out.println(e.toString());
        }

        return count;
    }

}
*/
