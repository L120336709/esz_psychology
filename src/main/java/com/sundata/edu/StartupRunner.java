package com.sundata.edu;

import com.sundata.edu.framework.core.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private RedisService redisService;

    @Override
    public void run(String... args) throws Exception {
        redisService.setSyn();
    }

}
