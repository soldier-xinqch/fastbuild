package com.fastbuild.controller;


import com.fastbuild.entity.AuthUserRole;
import com.fastbuild.entity.RestResult;
import com.fastbuild.service.AuthUserRoleService;
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
 * @description : AuthUserRole 控制器
 * ---------------------------------
 * @since 2018-01-18
 */
@RestController
@RequestMapping("/authUserRole")
public class AuthUserRoleController {
    private final Logger logger = LoggerFactory.getLogger(AuthUserRoleController.class);

    @Autowired
    public AuthUserRoleService authUserRoleService;

    /**
     * @description : 通过id获取AuthUserRole
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/getAuthUserRoleById", method = RequestMethod.GET)
    public Object getAuthUserRoleById(String id) {
        RestResult<AuthUserRole> resJson = new RestResult<>();
        try {
            AuthUserRole param = authUserRoleService.selectById(id);
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
     * @description : 通过id删除AuthUserRole
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/deleteAuthUserRoleById", method = RequestMethod.GET)
    public Object deleteAuthUserRoleById(String id) {
        RestResult<AuthUserRole> resJson = new RestResult<>();
        try {
            resJson.setSuccess(authUserRoleService.deleteById(id));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }

    /**
     * @description : 通过id更新AuthUserRole
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/updateAuthUserRoleById", method = RequestMethod.POST)
    public Object updateAuthUserRoleById(AuthUserRole param) {
        RestResult<AuthUserRole> resJson = new RestResult<>();
        try {
            resJson.setSuccess(authUserRoleService.updateById(param));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }

    /**
     * @description : 添加AuthUserRole
     * ---------------------------------
     * @author : xinqch
     * @since : Create in 2018-01-18
     */
    @RequestMapping(value = "/addAuthUserRole", method = RequestMethod.POST)
    public Object addAuthUserRole(AuthUserRole param) {
        RestResult<AuthUserRole> resJson = new RestResult<>();
        try {
            resJson.setSuccess(authUserRoleService.insert(param));
        } catch (Exception e) {
            resJson.setSuccess(false);
            resJson.setMessage("异常信息:{" + e.getClass().getName() + "}");
            logger.info("异常信息:{}" + e.getMessage());
        }
        return resJson;
    }
}