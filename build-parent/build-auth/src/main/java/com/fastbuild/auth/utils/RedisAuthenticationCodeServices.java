package com.fastbuild.auth.utils;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.util.Assert;

/**
 * 自定义生成授权码
 *  可以通过扩展AuthorizationCodeServices来覆写已有的生成规则。通过覆写createAuthorizationCode()方法可以设置成任意的生成规则
 *  http://blog.csdn.net/dong_19890208/article/details/74914852
 * @auther xinch
 * @create 2018/1/17 14:21
 */
public class RedisAuthenticationCodeServices  extends RandomValueAuthorizationCodeServices {

    private RedisConnectionFactory connectionFactory;

    // 随机码
//    private RandomValueStringGenerator generator = new RandomValueStringGenerator();

    public RedisAuthenticationCodeServices(RedisConnectionFactory connectionFactory) {
        Assert.notNull(connectionFactory, "RedisConnectionFactory required");
        this.connectionFactory = connectionFactory;
    }

    private RedisConnection getConnection() {
        return connectionFactory.getConnection();
    }

    @Override
    protected void store(String code, OAuth2Authentication oAuth2Authentication) {
       String userName =  oAuth2Authentication.getName();
        System.out.println("code = [" + code + "], oAuth2Authentication = [" + oAuth2Authentication + "]-userName:"+userName);
        //根据username 获取用户id 来进行校验
        RedisConnection conn = getConnection();
        try {
            conn.hSet(userName.getBytes("utf-8"), code.getBytes("utf-8"),
                    SerializationUtils.serialize(oAuth2Authentication));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        RedisConnection conn = getConnection();
        try {
            OAuth2Authentication authentication = null;

//            try {
//                authentication = SerializationUtils.deserialize(conn.hGet(generator.generate().getBytes("utf-8"), code.getBytes("utf-8")));
//            } catch (Exception e) {
//                return null;
//            }
//
//            if (null != authentication) {
//                conn.hDel(AUTH_CODE_KEY.getBytes("utf-8"), code.getBytes("utf-8"));
//            }

            return authentication;
        } catch (Exception e) {
            return null;
        } finally {
            conn.close();
        }
    }

}
