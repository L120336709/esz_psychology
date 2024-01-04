package com.sundata.edu.framework.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:spring/spring-dubbo.xml"})
public class DubboConfig {

}
