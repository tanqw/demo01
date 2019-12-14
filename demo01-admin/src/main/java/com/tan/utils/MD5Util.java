package com.tan.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class MD5Util {

    /**
     * 设置密码加密
     *
     * @param password
     * @return
     */
    public String setPwd(String password) {
        for (int k = 512; k > 0; k--) {
            password = getMD5(password);
        }
        return password;
    }

    /**
     * 对比密码是否正确
     *
     * @param password
     * @param MD5password
     * @return
     */
    public Boolean Check(String password, String MD5password) {
        password = getMD5(password);
        return password.equals(MD5password);
    }

    /**
     * 得到加密字符串
     *
     * @param password
     * @return
     */
    private String getMD5(String password) {
        String md5str = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] input = password.getBytes();
            byte[] buff = md.digest(input);
            md5str = bytesToHex(buff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str;
    }

    /**
     * 二进制转十六进制
     *
     * @param bytes
     * @return
     */
    private String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];
            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }
}
