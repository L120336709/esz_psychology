package com.sundata.edu.framework.util;

import org.springframework.util.DigestUtils;

public class Md5Utils {

    public static String md5(String data) throws Exception {
        //加密后的字符串
        String encodeStr= DigestUtils.md5DigestAsHex(data.getBytes());
        //System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
    }

    public static boolean verify(String data, String md5) throws Exception {
        return md5(data).equalsIgnoreCase(md5);
    }
}
