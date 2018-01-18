//package com.fastbuild.controller;
//
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import com.tdx..controller.AbstractController;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.baomidou.mybatisplus.plugins.Page;
//import com.fastbuild.service.AuthClientResourceService;
//import com.fastbuild.entity.common.DatatablesJSON;
//import com.fastbuild.entity.common.JSONResult;
//import com.fastbuild.entity.AuthClientResource;
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
// *   @description : AuthClientResource 控制器
// *   ---------------------------------
// *      @author xinqch
// *   @since 2018-01-18
// */
//@RestController
//@RequestMapping("/authClientResource")
//public class AuthClientResourceController extends AbstractController {
//private final Logger logger = LoggerFactory.getLogger(AuthClientResourceController.class);
//
//@Autowired
//public AuthClientResourceService authClientResourceService;
//
///**
// * @description : 获取分页列表
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/getAuthClientResourceList",method = RequestMethod.POST)
//public Object getAuthClientResourceList(AuthClientResource param , @RequestParam(value = "draw",defaultValue = "0") Integer draw,
//@RequestParam(value = "length") Integer length,
//@RequestParam(value = "start") Integer start) {
//        DatatablesJSON<AuthClientResource> resJson=new DatatablesJSON<>();
//        try {
//        Integer pageNo=getPageNo(start,length);
//        Page<AuthClientResource> page=new Page<AuthClientResource>(pageNo,length);
//    authClientResourceService.selectPage(page,new EntityWrapper<AuthClientResource>(param));
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
// * @description : 通过id获取AuthClientResource
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/getAuthClientResourceById",method = RequestMethod.GET)
//public Object getAuthClientResourceById(String id) {
//        JSONResult<AuthClientResource> resJson = new JSONResult<>();
//        try {
//    AuthClientResource param= authClientResourceService.selectById(id);
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
// * @description : 通过id删除AuthClientResource
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/deleteAuthClientResourceById",method = RequestMethod.GET)
//public Object deleteAuthClientResourceById(String id) {
//        JSONResult<AuthClientResource> resJson = new JSONResult<>();
//        try{
//        resJson.setSuccess(authClientResourceService.deleteById(id));
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//
///**
// * @description : 通过id更新AuthClientResource
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/updateAuthClientResourceById",method = RequestMethod.POST)
//public Object updateAuthClientResourceById(AuthClientResource param) {
//        JSONResult<AuthClientResource> resJson = new JSONResult<>();
//        try{
//        resJson.setSuccess(authClientResourceService.updateById(param));
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//
///**
// * @description : 添加AuthClientResource
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/addAuthClientResource",method = RequestMethod.POST)
//public Object addAuthClientResource(AuthClientResource param) {
//        JSONResult<AuthClientResource> resJson = new JSONResult<>();
//        try{
//        resJson.setSuccess(authClientResourceService.insert(param));
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//        }