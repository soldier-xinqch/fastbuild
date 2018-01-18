//package com.fastbuild.controller;
//
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.baomidou.mybatisplus.plugins.Page;
//import com.fastbuild.service.AuthRoleResourceService;
//import com.fastbuild.entity.common.DatatablesJSON;
//import com.fastbuild.entity.common.JSONResult;
//import com.fastbuild.entity.AuthRoleResource;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// *code is far away from bug with the animal protecting
// *  ┏┓　　　┏┓
// *┏┛┻━━━┛┻┓
// *┃　　　　　　　┃ 　
// *┃　　　━　　　┃
// *┃　┳┛　┗┳　┃
// *┃　　　　　　　┃
// *┃　　　┻　　　┃
// *┃　　　　　　　┃
// *┗━┓　　　┏━┛
// *　　┃　　　┃神兽保佑
// *　　┃　　　┃代码无BUG！
// *　　┃　　　┗━━━┓
// *　　┃　　　　　　　┣┓
// *　　┃　　　　　　　┏┛
// *　　┗┓┓┏━┳┓┏┛
// *　　　┃┫┫　┃┫┫
// *　　　┗┻┛　┗┻┛
// *
// *   @description : AuthRoleResource 控制器
// *   ---------------------------------
// *      @author xinqch
// *   @since 2018-01-18
// */
//@RestController
//@RequestMapping("/authRoleResource")
//public class AuthRoleResourceController {
//private final Logger logger = LoggerFactory.getLogger(AuthRoleResourceController.class);
//
//@Autowired
//public AuthRoleResourceService authRoleResourceService;
//
///**
// * @description : 获取分页列表
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/getAuthRoleResourceList",method = RequestMethod.POST)
//public Object getAuthRoleResourceList(AuthRoleResource param , @RequestParam(value = "draw",defaultValue = "0") Integer draw,
//@RequestParam(value = "length") Integer length,
//@RequestParam(value = "start") Integer start) {
//        DatatablesJSON<AuthRoleResource> resJson=new DatatablesJSON<>();
//        try {
//        Integer pageNo=getPageNo(start,length);
//        Page<AuthRoleResource> page=new Page<AuthRoleResource>(pageNo,length);
//    authRoleResourceService.selectPage(page,new EntityWrapper<AuthRoleResource>(param));
//        resJson.setDraw(draw++);
//        resJson.setRecordsTotal(page.getTotal());
//        resJson.setRecordsFiltered(page.getTotal());
//        resJson.setData(page.getRecords());
//        resJson.setSuccess(true);
//        }catch (Exception e){
//        resJson.setSuccess(false);
//        resJson.setError("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//
///**
// * @description : 通过id获取AuthRoleResource
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/getAuthRoleResourceById",method = RequestMethod.GET)
//public Object getAuthRoleResourceById(String id) {
//        JSONResult<AuthRoleResource> resJson = new JSONResult<>();
//        try {
//    AuthRoleResource param= authRoleResourceService.selectById(id);
//        resJson.setData(param);
//        resJson.setSuccess(true);
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//
///**
// * @description : 通过id删除AuthRoleResource
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/deleteAuthRoleResourceById",method = RequestMethod.GET)
//public Object deleteAuthRoleResourceById(String id) {
//        JSONResult<AuthRoleResource> resJson = new JSONResult<>();
//        try{
//        resJson.setSuccess(authRoleResourceService.deleteById(id));
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//
///**
// * @description : 通过id更新AuthRoleResource
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/updateAuthRoleResourceById",method = RequestMethod.POST)
//public Object updateAuthRoleResourceById(AuthRoleResource param) {
//        JSONResult<AuthRoleResource> resJson = new JSONResult<>();
//        try{
//        resJson.setSuccess(authRoleResourceService.updateById(param));
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//
///**
// * @description : 添加AuthRoleResource
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/addAuthRoleResource",method = RequestMethod.POST)
//public Object addAuthRoleResource(AuthRoleResource param) {
//        JSONResult<AuthRoleResource> resJson = new JSONResult<>();
//        try{
//        resJson.setSuccess(authRoleResourceService.insert(param));
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//        }