package com.fastbuild.service;

import com.fastbuild.common.StringCustomUtil;
import com.fastbuild.entity.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


/**
 * 测试service
 *  @Before：初始化方法
 *  @After：释放资源
 *  @Test：测试方法，在这里可以测试期望异常和超时时间
 *  @Ignore：忽略的测试方法
 *  @BeforeClass：针对所有测试，只执行一次，且必须为static void
 *  @AfterClass：针对所有测试，只执行一次，且必须为static void
 *  @RunWith：指定使用的单元测试执行类
 *
 *  @BeforeClass ==> @Before ==> @Test ==> @After ==> @AfterClass
 *  过程：就是先加载模拟的环境，再进行测试。
 * @auther xinch
 * @create 2018/1/19 14:36
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestService {

    @Autowired
    private  OauthClientDetailsService oauthClientDetailsService;
    @Autowired
    private AuthResourceService authResourceService;
    @Autowired
    private AuthRoleResourceService authRoleResourceService;
    @Autowired
    private AuthRoleService roleService;
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private AuthUserRoleService authUserRoleService;

    @Test
    @Ignore
    public void testInsertClient()  {
        OauthClientDetails client = new OauthClientDetails();
        client.setId(StringCustomUtil.getGUID());
        client.setClientId(StringCustomUtil.getGUID().toUpperCase());
        client.setClientName("测试客户端");
        client.setClientSecret(StringCustomUtil.getGUID());
        client.setResourceIds("ResourceID_1");
        client.setScope("select,read,write");
        client.setAuthorizedGrantTypes("authorization_code,password,implicit,client_credentials");
        client.setWebServerRedirectUri("www.baidu.com");
        client.setAuthorities("client");
        client.setAccessTokenValidity(360011);
        client.setRefreshTokenValidity(360011);
        client.setRegisterTime(new Date());
        boolean result1 = oauthClientDetailsService.insert(client);
        Assert.assertTrue(result1);

        OauthClientDetails result = oauthClientDetailsService.selectById("ab347903e730452bb3823fd4d65b5189");
        Assert.assertNotNull(result);
    }

    @Test
    @Ignore
    public void testAuthResourceService(){
        AuthResource authResource = new AuthResource();
        authResource.setId(StringCustomUtil.getGUID());
        authResource.setResourceId("ResourceID_1");
        authResource.setResourceName("测试资源");
        authResource.setResourcePreName("restSource");
        authResource.setResourceType("TEST");
        authResource.setResourceUrl("/getShop");
        boolean result1 =  authResourceService.insert(authResource);
        Assert.assertTrue(result1);
    }
    @Test
    public void testAuthRoleResourceService(){

    }
    @Test
    public void testAuthRoleService(){
        AuthRole role = new AuthRole();
        role.setId(StringCustomUtil.getGUID());
        role.setRoleAuth("SUPER"); // 超级管理员
        role.setRoleName("超级管理员");
        role.setRoleType("ADMIN"); // admin : 管理员， user 用户
        role.setStatus("0");
        boolean result1 =  roleService.insert(role);
        Assert.assertTrue(result1);
        role = new AuthRole();
        role.setId(StringCustomUtil.getGUID());
        role.setRoleAuth("ADMIN"); // 超级管理员
        role.setRoleName("管理员");
        role.setRoleType("ADMIN"); // admin : 管理员， user 用户
        role.setStatus("0");
        result1 =  roleService.insert(role);
        Assert.assertTrue(result1);
        role = new AuthRole();
        role.setId(StringCustomUtil.getGUID());
        role.setRoleAuth("USER"); // 超级管理员
        role.setRoleName("用户");
        role.setRoleType("USER"); // admin : 管理员， user 用户
        role.setStatus("0");
        result1 =  roleService.insert(role);
        Assert.assertTrue(result1);
    }
    @Test
    public void testAuthUserRoleService(){
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setId(StringCustomUtil.getGUID());
        authUserRole.setUserId("836c0cc1d7404b698d4becaa3315909c");
        authUserRole.setRoleId("ebbc9d5950584eb2ad0f552fc0782ce2");
        authUserRole.setRoleName("超级管理员");
        authUserRole.setRoleType("SUPER");
        authUserRole.setCreateTime(new Date());
        boolean result1 = authUserRoleService.insert(authUserRole);
        Assert.assertTrue(result1);
        authUserRole = new AuthUserRole();
        authUserRole.setId(StringCustomUtil.getGUID());
        authUserRole.setUserId("836c0cc1d7404b698d4becaa3315909c");
        authUserRole.setRoleId("f36e02d89e774dc8befe9545089b9177");
        authUserRole.setRoleName("管理员");
        authUserRole.setRoleType("ADMIN");
        authUserRole.setCreateTime(new Date());
        result1 = authUserRoleService.insert(authUserRole);
        Assert.assertTrue(result1);
        authUserRole = new AuthUserRole();
        authUserRole.setId(StringCustomUtil.getGUID());
        authUserRole.setUserId("836c0cc1d7404b698d4becaa3315909c");
        authUserRole.setRoleId("201083907e314a719ad9d0e3cd024864");
        authUserRole.setRoleName("用户");
        authUserRole.setRoleType("USER");
        authUserRole.setCreateTime(new Date());
        result1 = authUserRoleService.insert(authUserRole);
        Assert.assertTrue(result1);
    }
    @Test
    public void testAuthUserService(){
        AuthUser user = new AuthUser();
        user.setId(StringCustomUtil.getGUID());
        user.setUserName("测试用户");
        user.setUserPassword("e10adc3949ba59abbe56e057f20f883e");
        user.setUserEmail("xinchao32119@163.com");
        user.setUserMobile("18612322222");
        user.setRealName("xinqch");
        user.setUserMessage("this is the test user！！！");
        user.setUserStatus("0");
        user.setRegisterTime(new Date());
        boolean result1 =  authUserService.insert(user);
        Assert.assertTrue(result1);

    }



}
