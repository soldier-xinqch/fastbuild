package com.fastbuild.controller;


import com.fastbuild.entity.RestResult;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.fastbuild.service.OauthClientDetailsService;
import com.fastbuild.entity.OauthClientDetails;
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
 * @description : OauthClientDetails 控制器
 * ---------------------------------
 * @since 2018-01-22
 */
@RestController
@RequestMapping("/oauthClientDetails")
public class OauthClientDetailsController {
    private final Logger logger = LoggerFactory.getLogger(OauthClientDetailsController.class);

    @Autowired
    public OauthClientDetailsService oauthClientDetailsService;

    /**
     * @description : 通过id获取OauthClientDetails
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-22
     */
    @RequestMapping(value = "/getOauthClientDetailsById", method = RequestMethod.GET)
    public Object getOauthClientDetailsById(String id) {
        RestResult<OauthClientDetails> resJson = new RestResult<>();
        try {
            OauthClientDetails param = oauthClientDetailsService.selectById(id);
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
     * @description : 通过id删除OauthClientDetails
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-22
     */
    @RequestMapping(value = "/deleteOauthClientDetailsById", method = RequestMethod.GET)
    public Object deleteOauthClientDetailsById(String id) {
        RestResult<OauthClientDetails> resJson = new RestResult<>();
        try {
            resJson.setSuccess(oauthClientDetailsService.deleteById(id));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }

    /**
     * @description : 通过id更新OauthClientDetails
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-22
     */
    @RequestMapping(value = "/updateOauthClientDetailsById", method = RequestMethod.POST)
    public Object updateOauthClientDetailsById(OauthClientDetails param) {
        RestResult<OauthClientDetails> resJson = new RestResult<>();
        try {
            resJson.setSuccess(oauthClientDetailsService.updateById(param));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }

    /**
     * @description : 添加OauthClientDetails
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-22
     */
    @RequestMapping(value = "/addOauthClientDetails", method = RequestMethod.POST)
    public Object addOauthClientDetails(OauthClientDetails param) {
        RestResult<OauthClientDetails> resJson = new RestResult<>();
        try {
            resJson.setSuccess(oauthClientDetailsService.insert(param));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }
}