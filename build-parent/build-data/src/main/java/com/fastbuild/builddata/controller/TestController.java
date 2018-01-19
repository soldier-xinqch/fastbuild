package com.fastbuild.builddata.controller;

import com.fastbuild.builddata.entity.TestTab;
import com.fastbuild.builddata.service.TestTabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 测试Controller
 * https://gitee.com/boding1/pig-cloud
 * @auther xinch
 * @create 2017/12/22 17:15
 */
@SuppressWarnings("unused")
@RestController
@RequestMapping("/bizUser")
public class TestController {

    @Autowired
    private TestTabService testTabService;

    @RequestMapping(value = "/getShop", method = RequestMethod.GET)
    public @ResponseBody
    TestTab getShop(@RequestParam("id") String id) {
        System.out.println("id = [" + id + "]");
        TestTab t1 = testTabService.getById(id);
        System.out.println("id = [" + t1.getId()+"---"+ t1.getMessage()+ "]");
        TestTab t2 = testTabService.getByIdSlave(id);
        System.out.println("id = [" + t2.getId()+"---"+ t2.getMessage() + "]");
        TestTab tab = testTabService.selectById("1");
        return tab;
    }

    @RequestMapping(value = "/gettest", method = RequestMethod.GET)
    public @ResponseBody  String getShop1() {

        return "TEST-1";
    }


}
