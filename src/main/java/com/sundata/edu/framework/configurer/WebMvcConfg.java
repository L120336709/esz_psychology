package com.sundata.edu.framework.configurer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sundata.edu.framework.core.Resource;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author 侯鹏
 * @Date 2018-12-25 10:28
 * @Version 1.0
 */
@EnableWebMvc
@Configuration
public class WebMvcConfg implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Resource resource;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        converters.add(new ResourceHttpMessageConverter());
        converters.add(new AllEncompassingFormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        converters.add(jackson2HttpMessageConverter());
    }

    /**
     * 时间格式转换器,将Date类型统一转换为yyyy-MM-dd HH:mm:ss格式的字符串
     */
    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();

        //日期格式转换
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        //Long类型转String类型
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        mapper.registerModule(simpleModule);

        converter.setObjectMapper(mapper);
        return converter;
    }

    /**
     * 配置静态页面请求处理
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info(resource.WEB_UPLOAD_PATH);
        List<String> list = new ArrayList<>(5);
        list.add("classpath:/META-INF/resources/");
        list.add("classpath:/temp/");
        list.add("classpath:/resources/");
        list.add("classpath:/static/");
        list.add("classpath:/public/");
        list.add("file:" + resource.WEB_UPLOAD_PATH);
//        String[] RESOURCE_LOCATIONS = {"classpath:/static/"};
        String[] RESOURCE_LOCATIONS = new String[list.size()];
        list.toArray(RESOURCE_LOCATIONS);
        registry.addResourceHandler("/**")
                .addResourceLocations(RESOURCE_LOCATIONS);
    }


//    public void pp(){
//        // 待加密字符串
//        String pwd = "root";
//        StringEncryptor stringEncryptor = stringEncryptor();
//        String encrypt = stringEncryptor.encrypt(pwd);
//        System.err.println("【" + pwd + "】被加密成【" + encrypt + "】");
//
//
//        encrypt="GjH23dZvTacoR+v5wJCbPA==";//
//        String decrypt = stringEncryptor.decrypt(encrypt);
//        System.err.println("【" + encrypt + "】被解密成【" + decrypt + "】");
//        encrypt="5vHBQQCnndsHfKid1P/QF2I5vgsUQhszJ3WmxEVoVJBZfAVCzvWJlQ==";//5vHBQQCnndsHfKid1P/QF2I5vgsUQhszJ3WmxEVoVJBZfAVCzvWJlQ==
//          decrypt = stringEncryptor.decrypt(encrypt);
//        System.err.println("【" + encrypt + "】被解密成【" + decrypt + "】");
//
//    }


    @Bean("stringEncryptor")
    public StringEncryptor stringEncryptor() {

        //System.err.println("stringEncryptor===================");
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("dfklfjklfjsd#@34jfkj!~76786%&*+_&^&====");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }



}
