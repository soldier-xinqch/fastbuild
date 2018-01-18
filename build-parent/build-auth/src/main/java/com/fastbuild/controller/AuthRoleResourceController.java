package com.fastbuild.controller;


import com.fastbuild.entity.RestResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fastbuild.service.AuthRoleResourceService;
import com.fastbuild.entity.AuthRoleResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * @description : AuthRoleResource 控制器
 * ---------------------------------
 * @since 2018-01-18
 */
@RestController
@RequestMapping("/authRoleResource")
public class AuthRoleResourceController {
    private final Logger logger = LoggerFactory.getLogger(AuthRoleResourceController.class);

    @Autowired
    public AuthRoleResourceService authRoleResourceService;

    /**
     * @description : 通过id获取AuthRoleResource
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/getAuthRoleResourceById", method = RequestMethod.GET)
    public Object getAuthRoleResourceById(String id) {
        RestResult<AuthRoleResource> resJson = new RestResult<>();
        try {
            AuthRoleResource param = authRoleResourceService.selectById(id);
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
     * @description : 通过id删除AuthRoleResource
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/deleteAuthRoleResourceById", method = RequestMethod.GET)
    public Object deleteAuthRoleResourceById(String id) {
        RestResult<AuthRoleResource> resJson = new RestResult<>();
        try {
            resJson.setSuccess(authRoleResourceService.deleteById(id));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }

    /**
     * @description : 通过id更新AuthRoleResource
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/updateAuthRoleResourceById", method = RequestMethod.POST)
    public Object updateAuthRoleResourceById(AuthRoleResource param) {
        RestResult<AuthRoleResource> resJson = new RestResult<>();
        try {
            resJson.setSuccess(authRoleResourceService.updateById(param));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }

    /**
     * @description : 添加AuthRoleResource
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/addAuthRoleResource", method = RequestMethod.POST)
    public Object addAuthRoleResource(AuthRoleResource param) {
        RestResult<AuthRoleResource> resJson = new RestResult<>();
        try {
            resJson.setSuccess(authRoleResourceService.insert(param));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }
}