package com.sundata.edu.receive;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.sundata.edu.framework.core.RedisService;
import com.sundata.edu.receive.bean.OperationInfoData;
import com.sundata.edu.receive.bean.OrgInfoData;
import com.sundata.edu.receive.bean.OrgRequest;
import com.sundata.edu.service.OrginfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class OrgInfoReceive {
    @Autowired
    private OrginfoService organizationService;
    @Autowired
    private RedisService redisService;

    @RabbitListener(queues = "wagua.orgInfo")
    @RabbitHandler
    public void receiveData(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        try {
           // log.info("rabbit.orginfo-->{}", message);
            channel.basicAck(deliveryTag, false);
            if (!redisService.getSyn()) {
                //log.info("消息同步开关关闭");
                return;
            }
            pushData(JSON.parseObject(message, OperationInfoData.class));
        } catch (Exception ex) {
            //log.error("orginfo", ex);
        }
    }

    private void pushData(OperationInfoData operationInfoData) {
        try {
            Object data = operationInfoData.getData();
            if (data == null) {
                return;
            }
            OrgInfoData orgInfoData = JSON.parseObject(data.toString(), OrgInfoData.class);
            if (StringUtils.isEmpty(orgInfoData.getOrgname())) {
                return;
            }
            OrgRequest orgRequest = new OrgRequest();
            orgRequest.setOutOrgId(orgInfoData.getOrgid());
            orgRequest.setOutParentOrgId(orgInfoData.getPorgid());
            orgRequest.setOrgName(orgInfoData.getOrgname());
            orgRequest.setOrgType(orgInfoData.getOrgtype());
            orgRequest.setStatus(Integer.parseInt(orgInfoData.getStatus()));
            orgRequest.setElevel(orgInfoData.getElevel());
           // log.info("orginfo-->{}", JSON.toJSONString(orgRequest));
            organizationService.synOrgInfo(orgRequest);
        } catch (Exception e) {
            e.printStackTrace();
           // log.error("orginfo:" + e.getMessage());
        }
    }

}
