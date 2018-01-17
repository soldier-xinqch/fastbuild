package com.fastbuild.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * oauth2 授权页面定制
 * http://conkeyn.iteye.com/blog/2296406
 * @auther xinch
 * @create 2018/1/17 11:16
 */
@Controller
public class AuthPageController {


    /**
     *  授权失败
     *  未获取权限等
     * @return
     */
    @RequestMapping(value = "/auth/error", method = RequestMethod.GET)
    public String authError() {

        return "auth/error";
    }
}
