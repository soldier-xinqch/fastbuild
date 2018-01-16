package com.fastbuild.common;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 *  字符串工具类
 */
public class StringCustomUtil {

    private static final String randonLStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String randonStr = "abcdefghijklmnopqrstuvwxyz";

    private static final String randonNum = "0123456789";

    /**
     * 首字母大写
     */
    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    /**
     * 对字符串md5加密
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成随机字符串
     *
     * @param strLength
     * @param enFlag
     * @return
     */
    public static String getRandomStr(int strLength, boolean enFlag) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        if (enFlag) {
            String str = randonNum + randonLStr + randonStr;
            for (int i = 0; i < strLength; i++) {
                int number = random.nextInt(str.length());
                sb.append(str.charAt(number));
            }
            return sb.toString();
        } else {
            for (int i = 0; i < strLength; i++) {
                int number = random.nextInt(randonNum.length());
                sb.append(randonNum.charAt(number));
            }
            return sb.toString();
        }
    }

    public static String baseEncode(String encodeStr) throws UnsupportedEncodingException {
        // 获取加密对象
        Base64.Encoder encoder = Base64.getEncoder();
        // 加密
        byte[] encode = encoder.encode(encodeStr.getBytes());
        return new String(encode, "UTF-8");
    }

    public static String baseDecode(String decodeStr) throws UnsupportedEncodingException {
        // 获取解密对象
        Base64.Decoder decoder = Base64.getDecoder();
        // 解密
        byte[] decode = decoder.decode(decodeStr);
        return new String(decode, "UTF-8");
    }

    /**
     * 生成 [min,max]时间随机数
     * @param min
     * @param max
     * @return
     */
    public static int getRandomInt(int min, int max) {

        Random random = new Random();

        int s = random.nextInt(max) % (max - min + 1) + min;

        return s;
    }

    private static int LAST_NID = 1;
    private static long LAST_TIME = 0;


    public static String getUUID() {
        //return UUID.randomUUID().toString().replaceAll("\\-", "");
        return getLongId();
    }


    public synchronized static String getLongId(){
        long a = System.currentTimeMillis();
        if(a==LAST_TIME){
            LAST_NID++;
        }
        else{
            LAST_NID = 1;
            LAST_TIME = a;
        }

        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(a))+ StringUtils.leftPad(Integer.toString(LAST_NID), 3, "0");
    }

    public static String getGUID() {
        return UUID.randomUUID().toString().replaceAll("\\-", "");
    }


    public static String getUUID2() {
        return getTimeUUID();
    }
    public synchronized static String getTimeUUID(){
        long a = System.currentTimeMillis();
        if(a==LAST_TIME){
            LAST_NID++;
        }
        else{
            LAST_NID = 1;
            LAST_TIME = a;
        }

        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(a))+StringUtils.leftPad(Integer.toString(LAST_NID), 3, "0");
    }

}
