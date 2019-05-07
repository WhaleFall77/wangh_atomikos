package com.zy.fenbu.subtreasurydemo.publicdata.user.web;

import com.zy.fenbu.subtreasurydemo.privatedata.userinfo.service.UserInfoService;
import com.zy.fenbu.subtreasurydemo.publicdata.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/user")
@RestController
public class UserRest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("index")
    public void index(){
        System.out.println(111);
        try {
            userInfoService.test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
