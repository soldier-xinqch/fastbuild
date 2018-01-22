package com.fastbuild.auth.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.fastbuild.auth.model.AuthJwtUser;
import com.fastbuild.auth.service.IAuthUserDetailService;
import com.fastbuild.entity.AuthUser;
import com.fastbuild.entity.AuthUserRole;
import com.fastbuild.service.AuthUserRoleService;
import com.fastbuild.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户安全验证用户信息服务类
 *
 * @auther xinch
 * @create 2018/1/16 16:34
 */
@Service("authUserDetailService")
public class AuthUserDetailServiceImpl implements IAuthUserDetailService {


    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private AuthUserRoleService authUserRoleService;
    /**
     * 提供一种从用户名可以查到用户并返回的方法
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Wrapper<AuthUser> wrapper = new EntityWrapper<>();
        wrapper.eq("USER_NAME",userName);
        AuthUser user = authUserService.selectOne(wrapper);

        Wrapper<AuthUserRole>  roleWrapper = new EntityWrapper<>();
        roleWrapper.eq("USER_ID",user.getId());
        List<AuthUserRole> roles = authUserRoleService.selectList(roleWrapper);

        List<String> roleAuths = new ArrayList<>();
        for (AuthUserRole userRole: roles ) {
            roleAuths.add(userRole.getRoleType());
        }
        JSONObject roleAuthJson = new JSONObject();
        String roleStrs =  JSONArray.toJSONString(roleAuths);

        String userJson = JSONObject.toJSONString(user);
        System.out.println(userJson);
        JSONObject userJsonObj =  JSONObject.parseObject(userJson);
        userJsonObj.put("roles",JSONArray.parseArray(roleStrs));
        return AuthJwtUser.create(userJsonObj);
    }
}
