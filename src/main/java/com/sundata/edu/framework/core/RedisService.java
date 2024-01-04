package com.sundata.edu.framework.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Resource resource;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String key = "middleware.app.syn";

    public void setSyn() {
        Object value = stringRedisTemplate.opsForHash().get(key, resource.APP_KEY);
        if (value == null) {
            stringRedisTemplate.opsForHash().put(key, resource.APP_KEY, "1");
        }
    }

    public boolean getSyn() {
        try {
            Object value = stringRedisTemplate.opsForHash().get(key, resource.APP_KEY);
            if (value == null) {
                setSyn();
                return true;
            } else {
                return "1".equals((value + ""));
            }
        } catch (Exception ex) {
            logger.error("获取redis", ex);
            return true;
        }
    }
}
