package com.fastbuild.auth.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Jwt 保存用户信息实体 用于用户的认证
 *
 * @auther xinch
 * @create 2018/1/16 16:30
 */
public class AuthJwtUser extends User {

    private final String id;
    private final String username; //设置为account
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    /**
     *  设置权限用户信息
     * @param id
     * @param username
     * @param password
     * @param authorities
     */
    public AuthJwtUser(String id, String username, String password,  Collection<? extends GrantedAuthority> authorities) {
        super(username,password,authorities);
        // 如果用户被锁定 则抛出异常 并提示在5分钟后重新登陆

        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     *  根据用户信息构建认证用户信息实体
     * @param userJsonMsg
     * @return
     */
    @SuppressWarnings("Duplicates")
    public static AuthJwtUser create(JSONObject userJsonMsg) {
        try {
            String userId =  userJsonMsg.getString("id");
            Assert.assertNotNull(userId,"构建认证用户信息异常：原始用户信息id数据为空");
            String userName = userJsonMsg.getString("userName");
            Assert.assertNotNull(userName,"构建认证用户信息异常：原始用户信息userName数据为空");
            String userPasswd = userJsonMsg.getString("userPassword");
            Assert.assertNotNull(userPasswd,"构建认证用户信息异常：原始用户信息userPasswd数据为空");
            JSONArray rolesArrays = userJsonMsg.getJSONArray("roles");
//            Assert.assertNull(rolesArrays,"构建认证用户信息异常：原始用户信息roles数据为空");
            List<String> roleList = rolesArrays.toJavaList(String.class);
//            Assert.(roleList,"构建认证用户信息异常：原始用户信息roles数据转为异常");
            return new AuthJwtUser(userId,userName,userPasswd,mapToGrantedAuthorities(roleList));
        } catch (JSONException e) {
            e.printStackTrace();
        }
      return null;
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
