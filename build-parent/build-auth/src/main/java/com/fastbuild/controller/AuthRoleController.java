package com.fastbuild.controller;


import com.fastbuild.entity.AuthRole;
import com.fastbuild.entity.RestResult;
import com.fastbuild.service.AuthRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
 * @description : AuthRole 控制器
 * ---------------------------------
 * @since 2018-01-18
 */
@RestController
@RequestMapping("/authRole")
public class AuthRoleController {
    private final Logger logger = LoggerFactory.getLogger(AuthRoleController.class);

    @Autowired
    public AuthRoleService authRoleService;

    /**
     * @description : 通过id获取AuthRole
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/getAuthRoleById", method = RequestMethod.GET)
    public Object getAuthRoleById(String id) {
        RestResult<AuthRole> resJson = new RestResult<>();
        try {
            AuthRole param = authRoleService.selectById(id);
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
     * @description : 通过id删除AuthRole
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/deleteAuthRoleById", method = RequestMethod.GET)
    public Object deleteAuthRoleById(String id) {
        RestResult<AuthRole> resJson = new RestResult<>();
        try {
            resJson.setSuccess(authRoleService.deleteById(id));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }

    /**
     * @description : 通过id更新AuthRole
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/updateAuthRoleById", method = RequestMethod.POST)
    public Object updateAuthRoleById(AuthRole param) {
        RestResult<AuthRole> resJson = new RestResult<>();
        try {
            resJson.setSuccess(authRoleService.updateById(param));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }

    /**
     * @description : 添加AuthRole
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/addAuthRole", method = RequestMethod.POST)
    public Object addAuthRole(AuthRole param) {
        RestResult<AuthRole> resJson = new RestResult<>();
        try {
            resJson.setSuccess(authRoleService.insert(param));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }
}