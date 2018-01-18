package com.fastbuild.controller;


import com.fastbuild.entity.RestResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fastbuild.service.AuthResourceService;
import com.fastbuild.entity.AuthResource;
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
 *   @description : AuthResource 控制器
 *   ---------------------------------
 *      @author xinqch
 *   @since 2018-01-18
 */
@RestController
@RequestMapping("/authResource")
public class AuthResourceController {
private final Logger logger = LoggerFactory.getLogger(AuthResourceController.class);

@Autowired
public AuthResourceService authResourceService;

/**
 * @description : 通过id获取AuthResource
 * ---------------------------------
 * @author : xinqch
 * @since : Create in 2018-01-18
 */
@RequestMapping(value = "/getAuthResourceById",method = RequestMethod.GET)
public Object getAuthResourceById(String id) {
        RestResult<AuthResource> resJson = new RestResult<>();
        try {
    AuthResource param= authResourceService.selectById(id);
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
 * @description : 通过id删除AuthResource
 * ---------------------------------
 * @author : xinqch
 * @since : Create in 2018-01-18
 */
@RequestMapping(value = "/deleteAuthResourceById",method = RequestMethod.GET)
public Object deleteAuthResourceById(String id) {
        RestResult<AuthResource> resJson = new RestResult<>();
        try{
        resJson.setSuccess(authResourceService.deleteById(id));
        }catch (Exception e) {
        resJson.setSuccess(false);
        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
        logger.info("异常信息:{}"+e.getMessage());
        }
        return resJson;
        }

/**
 * @description : 通过id更新AuthResource
 * ---------------------------------
 * @author : xinqch
 * @since : Create in 2018-01-18
 */
@RequestMapping(value = "/updateAuthResourceById",method = RequestMethod.POST)
public Object updateAuthResourceById(AuthResource param) {
        RestResult<AuthResource> resJson = new RestResult<>();
        try{
        resJson.setSuccess(authResourceService.updateById(param));
        }catch (Exception e) {
        resJson.setSuccess(false);
        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
        logger.info("异常信息:{}"+e.getMessage());
        }
        return resJson;
        }

/**
 * @description : 添加AuthResource
 * ---------------------------------
 * @author : xinqch
 * @since : Create in 2018-01-18
 */
@RequestMapping(value = "/addAuthResource",method = RequestMethod.POST)
public Object addAuthResource(AuthResource param) {
        RestResult<AuthResource> resJson = new RestResult<>();
        try{
        resJson.setSuccess(authResourceService.insert(param));
        }catch (Exception e) {
        resJson.setSuccess(false);
        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
        logger.info("异常信息:{}"+e.getMessage());
        }
        return resJson;
        }
        }