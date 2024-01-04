package com.sundata.edu.receive;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.sundata.edu.api.bean.UserRequest;
import com.sundata.edu.framework.core.RedisService;
import com.sundata.edu.receive.bean.OperationInfoData;
import com.sundata.edu.receive.bean.UserInfoData;
import com.sundata.edu.service.UserinfoService;
import com.sundata.edu.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserInfoReceive {
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private RedisService redisService;

    //@RabbitListener(queues = "wagua.userInfo")
    @RabbitHandler
    public void receiveData(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) {
        try {
            //log.info("rabbit.userInfo:-->{}", message);
            channel.basicAck(deliveryTag, false);
            if (!redisService.getSyn()) {
               // log.info("消息同步开关关闭");
                return;
            }
            pushData(JsonUtils.parseObject(message, OperationInfoData.class));
        } catch (Exception ex) {
            //log.error("userInfo", ex);
        }
    }

    public void pushData(OperationInfoData operationInfoData) {
        try {
            if (operationInfoData.getData() == null) {
                return;
            }
            UserRequest userRequest = convert(operationInfoData);
            if (userRequest == null) {
                return;
            }
            //log.info("userInfo-->{}", JSON.toJSONString(userRequest));

            userinfoService.synUserInfo(userRequest);

        } catch (Exception e) {
            //log.error("userInfo:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /***
     *
     * @param operationInfoData
     * @return
     */
    public UserRequest convert(OperationInfoData operationInfoData) {
        UserInfoData userInfoData = JSON.parseObject(operationInfoData.getData().toString(), UserInfoData.class);
        UserRequest userRequest = null;
        if (userInfoData == null) {
            return userRequest;
        }
        //101教师 104 学校管理员 105 教育局管理员 106 教育局普通员工 107 学校普通员工
        if ("101".equals(userInfoData.getIdentity())
                || "102".equals(userInfoData.getIdentity())
                || "103".equals(userInfoData.getIdentity())
                || "104".equals(userInfoData.getIdentity())
                || "105".equals(userInfoData.getIdentity())
                || "106".equals(userInfoData.getIdentity())
                || "107".equals(userInfoData.getIdentity())) {
            userRequest = new UserRequest();
            userRequest.setRealName(userInfoData.getPersonName());
            userRequest.setOrgId(userInfoData.getOrgId());
            userRequest.setIdentity(Integer.parseInt(userInfoData.getIdentity()));
            userRequest.setStatus(Integer.parseInt(userInfoData.getState()));
            userRequest.setUserId(userInfoData.getUserId());
            userRequest.setIdCard(userInfoData.getIdCard());
            if (StringUtils.isNotEmpty(userInfoData.getClassLevel())) {
                String newGradeId = userInfoData.getClassLevel();
                newGradeId = newGradeId.replace("级", "");
                userRequest.setGradeId(newGradeId);
            }
            userRequest.setClassId(userInfoData.getClassId());
            userRequest.setUserNo(userInfoData.getUserNo());
            userRequest.setGender(userInfoData.getGender());
            userRequest.setNational(userInfoData.getNational());
            userRequest.setNativePlace(userInfoData.getNativePlace());
        }
        return userRequest;
    }
}
