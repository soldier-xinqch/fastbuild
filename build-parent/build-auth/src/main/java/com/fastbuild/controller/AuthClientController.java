//package com.fastbuild..controller;
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
//import com.fastbuild..service.AuthClientService;
//import com.fastbuild..entity.common.DatatablesJSON;
//import com.fastbuild..entity.common.JSONResult;
//import com.fastbuild..entity.AuthClient;
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
// *   @description : AuthClient 控制器
// *   ---------------------------------
// *      @author xinqch
// *   @since 2018-01-18
// */
//@RestController
//@RequestMapping("/authClient")
//public class AuthClientController extends AbstractController {
//private final Logger logger = LoggerFactory.getLogger(AuthClientController.class);
//
//@Autowired
//public AuthClientService authClientService;
//
///**
// * @description : 获取分页列表
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/getAuthClientList",method = RequestMethod.POST)
//public Object getAuthClientList(AuthClient param , @RequestParam(value = "draw",defaultValue = "0") Integer draw,
//@RequestParam(value = "length") Integer length,
//@RequestParam(value = "start") Integer start) {
//        DatatablesJSON<AuthClient> resJson=new DatatablesJSON<>();
//        try {
//        Integer pageNo=getPageNo(start,length);
//        Page<AuthClient> page=new Page<AuthClient>(pageNo,length);
//    authClientService.selectPage(page,new EntityWrapper<AuthClient>(param));
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
// * @description : 通过id获取AuthClient
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/getAuthClientById",method = RequestMethod.GET)
//public Object getAuthClientById(String id) {
//        JSONResult<AuthClient> resJson = new JSONResult<>();
//        try {
//    AuthClient param= authClientService.selectById(id);
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
// * @description : 通过id删除AuthClient
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/deleteAuthClientById",method = RequestMethod.GET)
//public Object deleteAuthClientById(String id) {
//        JSONResult<AuthClient> resJson = new JSONResult<>();
//        try{
//        resJson.setSuccess(authClientService.deleteById(id));
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//
///**
// * @description : 通过id更新AuthClient
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/updateAuthClientById",method = RequestMethod.POST)
//public Object updateAuthClientById(AuthClient param) {
//        JSONResult<AuthClient> resJson = new JSONResult<>();
//        try{
//        resJson.setSuccess(authClientService.updateById(param));
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//
///**
// * @description : 添加AuthClient
// * ---------------------------------
// * @author : xinqch
// * @since : Create in 2018-01-18
// */
//@RequestMapping(value = "/addAuthClient",method = RequestMethod.POST)
//public Object addAuthClient(AuthClient param) {
//        JSONResult<AuthClient> resJson = new JSONResult<>();
//        try{
//        resJson.setSuccess(authClientService.insert(param));
//        }catch (Exception e) {
//        resJson.setSuccess(false);
//        resJson.setMessage("异常信息:{"+e.getClass().getName()+"}");
//        logger.info("异常信息:{}"+e.getMessage());
//        }
//        return resJson;
//        }
//        }