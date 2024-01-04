
package com.sundata.edu.framework.configurer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    @Bean
    public Queue gradeInfo() {
        return new Queue("wagua.gradeInfo");
    }

    @Bean
    public Queue classInfo() {
        return new Queue("wagua.classInfo");
    }

    @Bean
    public Queue userInfo() {
        return new Queue("wagua.userInfo");
    }

    @Bean
    public Queue orgInfo() {
        return new Queue("wagua.orgInfo");
    }

    @Bean
    Binding bindingGradeInfoToExchange(Queue gradeInfo, FanoutExchange gradeInfoExchange) {
        return BindingBuilder.bind(gradeInfo).to(gradeInfoExchange);
    }

    @Bean
    Binding bindingClassInfoToExchange(Queue classInfo, FanoutExchange classInfoExchange) {
        return BindingBuilder.bind(classInfo).to(classInfoExchange);
    }

    @Bean
    Binding bindingUserInfoToExchange(Queue userInfo, FanoutExchange userInfoExchange) {
        return BindingBuilder.bind(userInfo).to(userInfoExchange);
    }

    @Bean
    Binding bindingOrgInfoToExchange(Queue orgInfo, FanoutExchange orgInfoExchange) {
        return BindingBuilder.bind(orgInfo).to(orgInfoExchange);
    }

    @Bean
    FanoutExchange gradeInfoExchange() {
        return new FanoutExchange("middleware.exchange.gradeinfo");
    }

    @Bean
    FanoutExchange classInfoExchange() {
        return new FanoutExchange("middleware.exchange.classinfo");
    }

    @Bean
    FanoutExchange userInfoExchange() {
        return new FanoutExchange("middleware.exchange.userinfo");
    }

    @Bean
    FanoutExchange orgInfoExchange() {
        return new FanoutExchange("middleware.exchange.orginfo");
    }

}


