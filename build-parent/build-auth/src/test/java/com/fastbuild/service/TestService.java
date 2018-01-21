package com.fastbuild.service;

import com.fastbuild.common.StringCustomUtil;
import com.fastbuild.entity.AuthClient;
import com.fastbuild.entity.TestTab;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public AuthClientService authClientService;

    @Autowired
    private TestTabService testTabService;

    @Test
    public void getShop() {
        String id ="1";
        System.out.println("id = [" + id + "]");
        TestTab t1 = testTabService.getById(id);
        System.out.println("id = [" + t1.getId()+"---"+ t1.getMessage()+ "]");
        TestTab t2 = testTabService.getByIdSlave(id);
        System.out.println("id = [" + t2.getId()+"---"+ t2.getMessage() + "]");
    }

    @Test
    @Ignore
    public void testInsertClient()  {

        AuthClient result = authClientService.selectById("1");
        Assert.assertNotNull(result);
//
//        AuthClient client = new AuthClient();
//        client.setId(StringCustomUtil.getGUID());
//        client.setClientId(StringCustomUtil.getGUID().toUpperCase());
//        client.setClientName("测试客户端");
//        client.setClientPassword(StringCustomUtil.getGUID());
//        client.setAuthType("authorization_code,password,implicit,client_credentials");
//        client.setAuthScope("select,read,write");
//        client.setExpireTime(360011);
//        client.setRegisterTime(new Date());
//        boolean result = authClientService.insert(client);
//        Assert.assertTrue(result);
    }


}
