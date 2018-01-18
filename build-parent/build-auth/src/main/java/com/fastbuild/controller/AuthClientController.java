package com.fastbuild.controller;


import com.fastbuild.entity.RestResult;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.fastbuild.service.AuthClientService;
import com.fastbuild.entity.AuthClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * code is far away from bug with the animal protecting
 *   ┏┓　　　┏┓
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
 * @description : AuthClient 控制器
 * ---------------------------------
 * @since 2018-01-18
 */
@RestController
@RequestMapping("/authClient")
public class AuthClientController {
    private final Logger logger = LoggerFactory.getLogger(AuthClientController.class);

    @Autowired
    public AuthClientService authClientService;

    /**
     * @description : 通过id获取AuthClient
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/getAuthClientById", method = RequestMethod.GET)
    public Object getAuthClientById(String id) {
        RestResult<AuthClient> resJson = new RestResult<>();
        try {
            AuthClient param = authClientService.selectById(id);
            resJson.setDataObject(param);
            resJson.setSuccess(true);
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }

    /**
     * @description : 通过id删除AuthClient
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/deleteAuthClientById", method = RequestMethod.GET)
    public Object deleteAuthClientById(String id) {
        RestResult<AuthClient> resJson = new RestResult<>();
        try {
            resJson.setSuccess(authClientService.deleteById(id));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }

    /**
     * @description : 通过id更新AuthClient
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/updateAuthClientById", method = RequestMethod.POST)
    public Object updateAuthClientById(AuthClient param) {
        RestResult<AuthClient> resJson = new RestResult<>();
        try {
            resJson.setSuccess(authClientService.updateById(param));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }

    /**
     * @description : 添加AuthClient
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/addAuthClient", method = RequestMethod.POST)
    public Object addAuthClient(AuthClient param) {
        RestResult<AuthClient> resJson = new RestResult<>();
        try {
            resJson.setSuccess(authClientService.insert(param));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }
}