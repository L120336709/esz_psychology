package com.sundata.edu.util;


import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * json工具类
 */
public class JsonUtils {

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     *
     * @param data
     * @return
     */
    public static String toJSONString(Object data) {
        return JSON.toJSONString(data);
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @return
     * @param: clazz 对象中的object类型
     */
    public static <T> T parseObject(String jsonData, Class<T> beanType) {
        try {
            return JSON.parseObject(jsonData,beanType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> parseArray(String jsonData, Class<T> beanType) {
        try {
            return JSON.parseArray(jsonData,beanType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
