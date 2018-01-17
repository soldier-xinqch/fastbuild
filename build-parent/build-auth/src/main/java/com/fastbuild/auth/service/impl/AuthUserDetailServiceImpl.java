package com.fastbuild.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fastbuild.auth.model.AuthJwtUser;
import com.fastbuild.auth.service.IAuthUserDetailService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户安全验证用户信息服务类
 *
 * @auther xinch
 * @create 2018/1/16 16:34
 */
@Service("authUserDetailService")
public class AuthUserDetailServiceImpl implements IAuthUserDetailService {


    /**
     * 提供一种从用户名可以查到用户并返回的方法
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        /**TODO:此处需要写明从用户表里面跟根据用户account查询用户的方法**/
//        User user =new User();
//        user.setAccount("17319237587");
//        user.setPwd("123");
//        user.setUserId(1L);
//        List<String> roles=new ArrayList<>();
//        roles.add("ADMIN");
//        user.setRoles(roles);
        JSONObject jsonObject = new JSONObject();
        return AuthJwtUser.create(jsonObject);
    }
}
