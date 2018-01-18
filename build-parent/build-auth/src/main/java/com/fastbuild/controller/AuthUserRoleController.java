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
//import com.fastbuild.service.AuthUserRoleService;
//import com.fastbuild.entity.common.DatatablesJSON;
//import com.fastbuild.entity.common.JSONResult;
//import com.fastbuild.entity.AuthUserRole;
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
// *   @description : AuthUserRole 控制器
// *   ---------------------------------
// *      @author xinqch
// *   @since 2018-01-18
// */
//@RestController
//@RequestMapping("/authUserRole")
//public class AuthUserRoleController {
//private final Logger logger = LoggerFactory.getLogger(AuthUserRoleController.class);
//
//@Autowired
//public AuthUserRoleService authUserRoleService;
//
///**
// * @description : 获取分页列表
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/getAuthUserRoleList",method = RequestMethod.POST)
//public Object getAuthUserRoleList(AuthUserRole param , @RequestParam(value = "draw",defaultValue = "0") Integer draw,
//@RequestParam(value = "length") Integer length,
//@RequestParam(value = "start") Integer start) {
//        DatatablesJSON<AuthUserRole> resJson=new DatatablesJSON<>();
//        try {
//        Integer pageNo=getPageNo(start,length);
//        Page<AuthUserRole> page=new Page<AuthUserRole>(pageNo,length);
//    authUserRoleService.selectPage(page,new EntityWrapper<AuthUserRole>(param));
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
// * @description : 通过id获取AuthUserRole
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/getAuthUserRoleById",method = RequestMethod.GET)
//public Object getAuthUserRoleById(String id) {
//        JSONResult<AuthUserRole> resJson = new JSONResult<>();
//        try {
//    AuthUserRole param= authUserRoleService.selectById(id);
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
// * @description : 通过id删除AuthUserRole
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/deleteAuthUserRoleById",method = RequestMethod.GET)
//public Object deleteAuthUserRoleById(String id) {
//        JSONResult<AuthUserRole> resJson = new JSONResult<>();
//        try{
//        resJson.setSuccess(authUserRoleService.deleteById(id));
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//
///**
// * @description : 通过id更新AuthUserRole
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/updateAuthUserRoleById",method = RequestMethod.POST)
//public Object updateAuthUserRoleById(AuthUserRole param) {
//        JSONResult<AuthUserRole> resJson = new JSONResult<>();
//        try{
//        resJson.setSuccess(authUserRoleService.updateById(param));
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//
///**
// * @description : 添加AuthUserRole
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/addAuthUserRole",method = RequestMethod.POST)
//public Object addAuthUserRole(AuthUserRole param) {
//        JSONResult<AuthUserRole> resJson = new JSONResult<>();
//        try{
//        resJson.setSuccess(authUserRoleService.insert(param));
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//        }