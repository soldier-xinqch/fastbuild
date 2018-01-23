package com.fastbuild.controller;


import com.fastbuild.entity.AuthUser;
import com.fastbuild.entity.RestResult;
import com.fastbuild.service.AuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author xinqch
 * @description : AuthUser 控制器
 * ---------------------------------
 * @since 2018-01-18
 */
@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/authUser")
public class AuthUserController {
    private final Logger logger = LoggerFactory.getLogger(AuthUserController.class);

    @Autowired
    public AuthUserService authUserService;

    /**
     * @description : 通过id删除AuthUser
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/deleteAuthUserById", method = RequestMethod.GET)
    public Object deleteAuthUserById(String id) {
        RestResult<AuthUser> resJson = new RestResult<>();
        try {
            resJson.setSuccess(authUserService.deleteById(id));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }

    /**
     * @description : 通过id更新AuthUser
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/updateAuthUserById", method = RequestMethod.POST)
    public Object updateAuthUserById(AuthUser param) {
        RestResult<AuthUser> resJson = new RestResult<>();
        try {
            resJson.setSuccess(authUserService.updateById(param));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }

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