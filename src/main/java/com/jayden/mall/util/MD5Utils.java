package com.jayden.mall.util;

import com.jayden.mall.common.Constant;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String getMD5Str(String strValue) throws NoSuchAlgorithmException {
        MessageDigest md5=MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest((strValue+ Constant.SALT).getBytes()));
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
       String pwd= getMD5Str("666666");
        System.out.println(pwd);
    }
}
