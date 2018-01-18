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
//import com.fastbuild.service.AuthRoleService;
//import com.fastbuild.entity.common.DatatablesJSON;
//import com.fastbuild.entity.common.JSONResult;
//import com.fastbuild.entity.AuthRole;
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
// *   @description : AuthRole 控制器
// *   ---------------------------------
// *      @author xinqch
// *   @since 2018-01-18
// */
//@RestController
//@RequestMapping("/authRole")
//public class AuthRoleController {
//private final Logger logger = LoggerFactory.getLogger(AuthRoleController.class);
//
//@Autowired
//public AuthRoleService authRoleService;
//
///**
// * @description : 获取分页列表
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/getAuthRoleList",method = RequestMethod.POST)
//public Object getAuthRoleList(AuthRole param , @RequestParam(value = "draw",defaultValue = "0") Integer draw,
//@RequestParam(value = "length") Integer length,
//@RequestParam(value = "start") Integer start) {
//        DatatablesJSON<AuthRole> resJson=new DatatablesJSON<>();
//        try {
//        Integer pageNo=getPageNo(start,length);
//        Page<AuthRole> page=new Page<AuthRole>(pageNo,length);
//    authRoleService.selectPage(page,new EntityWrapper<AuthRole>(param));
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
// * @description : 通过id获取AuthRole
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/getAuthRoleById",method = RequestMethod.GET)
//public Object getAuthRoleById(String id) {
//        JSONResult<AuthRole> resJson = new JSONResult<>();
//        try {
//    AuthRole param= authRoleService.selectById(id);
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
// * @description : 通过id删除AuthRole
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/deleteAuthRoleById",method = RequestMethod.GET)
//public Object deleteAuthRoleById(String id) {
//        JSONResult<AuthRole> resJson = new JSONResult<>();
//        try{
//        resJson.setSuccess(authRoleService.deleteById(id));
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//
///**
// * @description : 通过id更新AuthRole
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/updateAuthRoleById",method = RequestMethod.POST)
//public Object updateAuthRoleById(AuthRole param) {
//        JSONResult<AuthRole> resJson = new JSONResult<>();
//        try{
//        resJson.setSuccess(authRoleService.updateById(param));
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//
///**
// * @description : 添加AuthRole
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/addAuthRole",method = RequestMethod.POST)
//public Object addAuthRole(AuthRole param) {
//        JSONResult<AuthRole> resJson = new JSONResult<>();
//        try{
//        resJson.setSuccess(authRoleService.insert(param));
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//        }