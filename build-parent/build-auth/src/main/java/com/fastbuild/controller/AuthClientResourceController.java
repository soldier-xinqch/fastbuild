package com.fastbuild.controller;


import com.fastbuild.entity.RestResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fastbuild.service.AuthClientResourceService;
import com.fastbuild.entity.AuthClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *code is far away from bug with the animal protecting
 *  ┏┓　　　┏┓
 *┏┛┻━━━┛┻┓
 *┃　　　　　　　┃ 　
 *┃　　　━　　　┃
 *┃　┳┛　┗┳　┃
 *┃　　　　　　　┃
 *┃　　　┻　　　┃
 *┃　　　　　　　┃
 *┗━┓　　　┏━┛
 *　　┃　　　┃神兽保佑
 *　　┃　　　┃代码无BUG！
 *　　┃　　　┗━━━┓
 *　　┃　　　　　　　┣┓
 *　　┃　　　　　　　┏┛
 *　　┗┓┓┏━┳┓┏┛
 *　　　┃┫┫　┃┫┫
 *　　　┗┻┛　┗┻┛
 *
 *   @description : AuthClientResource 控制器
 *   ---------------------------------
 *      @author xinqch
 *   @since 2018-01-18
 */
@RestController
@RequestMapping("/authClientResource")
public class AuthClientResourceController {
private final Logger logger = LoggerFactory.getLogger(AuthClientResourceController.class);

@Autowired
public AuthClientResourceService authClientResourceService;

/**
 * @description : 通过id获取AuthClientResource
 * ---------------------------------
 * @author : xinqch
 * @since : Create in 2018-01-18
 */
@RequestMapping(value = "/getAuthClientResourceById",method = RequestMethod.GET)
public Object getAuthClientResourceById(String id) {
        RestResult<AuthClientResource> resJson = new RestResult<>();
        try {
    AuthClientResource param= authClientResourceService.selectById(id);
        resJson.setDataObject(param);
        resJson.setSuccess(true);
        }catch (Exception e) {
        resJson.setSuccess(false);
        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
        logger.info("异常信息:{}"+e.getMessage());
        }
        return resJson;
        }

/**
 * @description : 通过id删除AuthClientResource
 * ---------------------------------
 * @author : xinqch
 * @since : Create in 2018-01-18
 */
@RequestMapping(value = "/deleteAuthClientResourceById",method = RequestMethod.GET)
public Object deleteAuthClientResourceById(String id) {
        RestResult<AuthClientResource> resJson = new RestResult<>();
        try{
        resJson.setSuccess(authClientResourceService.deleteById(id));
        }catch (Exception e) {
        resJson.setSuccess(false);
        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
        logger.info("异常信息:{}"+e.getMessage());
        }
        return resJson;
        }

/**
 * @description : 通过id更新AuthClientResource
 * ---------------------------------
 * @author : xinqch
 * @since : Create in 2018-01-18
 */
@RequestMapping(value = "/updateAuthClientResourceById",method = RequestMethod.POST)
public Object updateAuthClientResourceById(AuthClientResource param) {
        RestResult<AuthClientResource> resJson = new RestResult<>();
        try{
        resJson.setSuccess(authClientResourceService.updateById(param));
        }catch (Exception e) {
        resJson.setSuccess(false);
        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
        logger.info("异常信息:{}"+e.getMessage());
        }
        return resJson;
        }

/**
 * @description : 添加AuthClientResource
 * ---------------------------------
 * @author : xinqch
 * @since : Create in 2018-01-18
 */
@RequestMapping(value = "/addAuthClientResource",method = RequestMethod.POST)
public Object addAuthClientResource(AuthClientResource param) {
        RestResult<AuthClientResource> resJson = new RestResult<>();
        try{
        resJson.setSuccess(authClientResourceService.insert(param));
        }catch (Exception e) {
        resJson.setSuccess(false);
        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
        logger.info("异常信息:{}"+e.getMessage());
        }
        return resJson;
        }
        }