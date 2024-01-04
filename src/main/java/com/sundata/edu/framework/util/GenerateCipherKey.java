package com.sundata.edu.framework.util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

/**
 * @author : wangck
 * @date : 2020/12/31 上午9:52
 */
public class GenerateCipherKey {
    /**
     * 随机生成秘钥，参考org.apache.shiro.crypto.AbstractSymmetricCipherService#generateNewKey(int)
     *
     * @return
     */
    public static byte[] generateNewKey() {
        KeyGenerator kg;
        try {
            kg = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            String msg = "Unable to acquire AES algorithm.  This is required to function.";
            throw new IllegalStateException(msg, e);
        }

        kg.init(128);
        SecretKey key = kg.generateKey();
        byte[] encoded = key.getEncoded();
        return encoded;
    }
}
