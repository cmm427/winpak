package com.jushi.winpak.util;

import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

public class AESDecode {
    /**
     * 计算MD5
     */
    public static String MD5(String s){
        if(s == null){
            return null;
        }
        String encodeStr = "";
        try{
            byte[] utfBytes = s.getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(utfBytes);
            byte[] md5Bytes = messageDigest.digest();
            BASE64Encoder base64Encoder = new BASE64Encoder();
            encodeStr = base64Encoder.encode(md5Bytes);
        }catch (Exception e){
            throw new java.lang.Error("Failed to generate MD5: " + e.getMessage());
        }
        return encodeStr;
    }

    public static String HMACSha1(String data, String key){
        String result;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            result = (new BASE64Encoder()).encode(rawHmac);

        }catch (Exception e){
            throw new Error("Failed to generate HMAC: " + e.getMessage());
        }
        return result;
    }

}
