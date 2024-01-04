package com.sundata.edu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author : wangck
 * @date : 2021/7/29 下午5:55
 */
@Configuration
@ImportResource({"classpath:spring/spring-bean.xml"})
public class RestTemplateConfig {
}
