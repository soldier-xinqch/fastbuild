package com.fastbuild.controller;

import com.fastbuild.entity.AuthUser;
import com.fastbuild.entity.RestResult;
import com.fastbuild.service.AuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 登陆注册
 *
 * @auther xinch
 * @create 2018/1/23 13:01
 */
@Controller
@RequestMapping("login")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(AuthUserController.class);

    @Autowired
    public AuthUserService authUserService;

    /**
     * @description : 添加AuthUser
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/addAuthUser", method = RequestMethod.POST)
    public Object addAuthUser(AuthUser param) {
        RestResult<AuthUser> resJson = new RestResult<>();
        try {
            resJson.setSuccess(authUserService.insert(param));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }

}
