package com.sundata.edu.receive;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.sundata.edu.domain.Classinfo;
import com.sundata.edu.enums.OperationEnum;
import com.sundata.edu.framework.core.RedisService;
import com.sundata.edu.receive.bean.ClassInfoData;
import com.sundata.edu.receive.bean.OperationInfoData;
import com.sundata.edu.service.ClassinfoService;
import com.sundata.edu.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClassInfoReceive {
    @Autowired
    private RedisService redisService;
    @Autowired
    private ClassinfoService classinfoService;

    @RabbitListener(queues = "wagua.classInfo")
    @RabbitHandler
    public void receiveData(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) {
        try {
            //log.info("rabbit.classInfo:-->{}", message);
            channel.basicAck(deliveryTag, false);
            if (!redisService.getSyn()) {
               // log.info("消息同步开关关闭");
                return;
            }
            pushData(JsonUtils.parseObject(message, OperationInfoData.class));
        } catch (Exception ex) {
           // log.error("classInfo", ex);
        }
    }

    public void pushData(OperationInfoData operationInfoData) {
        Object data = operationInfoData.getData();
        if (data == null) {
            return;
        }
        ClassInfoData classInfoData = JSON.parseObject(data.toString(), ClassInfoData.class);
        if (StringUtils.isEmpty(classInfoData.getClassId())) {
            return;
        }
        Classinfo classinfo = classinfoService.getClassinfoByClassId(classInfoData.getClassId());

        if (classinfo == null) {
            classinfo = new Classinfo();
            BeanUtils.copyProperties(classInfoData, classinfo);
            //因为数据库中schoolId为vachar类型，但是request中的schoolId为int类型，上面一句代码赋值不上
            classinfo.setSchoolId(classInfoData.getSchoolId() + "");
            String newGradeId = classInfoData.getClassLevel();
            newGradeId = newGradeId.replace("级", "");
            classinfo.setGradeId(newGradeId);
            classinfo.setGradeName(classInfoData.getClassLevel());
            classinfoService.saveClassinfo(classinfo, OperationEnum.INSERT);
        } else {
            BeanUtils.copyProperties(classInfoData, classinfo);
            classinfo.setSchoolId(classInfoData.getSchoolId() + "");
            String newGradeId = classInfoData.getClassLevel();
            newGradeId = newGradeId.replace("级", "");
            classinfo.setGradeId(newGradeId);
            classinfo.setGradeName(classInfoData.getClassLevel());
            classinfoService.saveClassinfo(classinfo, OperationEnum.UPDATE);
        }
    }

}
