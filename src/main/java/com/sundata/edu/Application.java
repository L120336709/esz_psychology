package com.sundata.edu;

import com.sundata.edu.util.QRCodeUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.sundata.edu.dao")
public class Application extends SpringBootServletInitializer {

    public Application(){
        super();
        setRegisterErrorPageFilter(false);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        //生成二维码
        //QRCodeUtils.createQrCodeImage("http://kccx.eszedu.com/yk/kccx",1100,"F:\\SDProject\\WorkFile\\ykcx.png");
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        for (String profile : activeProfiles) {
            System.out.println(String.format("当前使用profile为:%s环境", profile));
        }
    }
}

