package com.fastbuild.controller;

import com.fastbuild.entity.TestTab;
import com.fastbuild.service.TestTabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 测试Controller
 *
 * @auther xinch
 * @create 2017/12/22 17:15
 */
@Controller
public class TestController {

    @Autowired
    private TestTabService testTabService;

    @RequestMapping(value = "/getShop", method = RequestMethod.GET)
    public @ResponseBody  TestTab getShop(@RequestParam("id") String id) {
        System.out.println("id = [" + id + "]");
        TestTab t1 = testTabService.getById(id);
        System.out.println("id = [" + t1.getId()+"---"+ t1.getMessage()+ "]");
        TestTab t2 = testTabService.getByIdSlave(id);
        System.out.println("id = [" + t2.getId()+"---"+ t2.getMessage() + "]");
        return t1;
    }

    @RequestMapping(value = "/gettest", method = RequestMethod.GET)
    public @ResponseBody  String getShop1() {

        return "TEST-1";
    }

    @GetMapping("/product/{id}")
    public @ResponseBody String getProduct(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public @ResponseBody String getOrder(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }
}
