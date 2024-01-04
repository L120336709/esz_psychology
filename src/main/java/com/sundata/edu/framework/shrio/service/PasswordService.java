package com.sundata.edu.framework.shrio.service;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author 侯鹏
 * @Date 2018-12-21 22:41
 * @Version 1.0
 */
@Component
public class PasswordService {

    /**
     * 用户加密
     *
     * @param password
     * @param salt
     * @return
     */
    private String encryptPassword(String password, String salt) {
        return new Md5Hash(password + salt).toHex();
    }

    /**
     * 用户加密
     *
     * @param password
     * @return
     */
    public String getPassword(String password) {
        return encryptPassword(password, "@123456");
    }

}
