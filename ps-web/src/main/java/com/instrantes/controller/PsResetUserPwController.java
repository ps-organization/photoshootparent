package com.instrantes.controller;

import com.instrantes.service.PsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/resetpw")
public class PsResetUserPwController {

    @Autowired
    PsUserService psUserService;

    @RequestMapping(value = "/resetuserpw", method = RequestMethod.POST)
    @ResponseBody
    public String resetUserPw(@RequestParam("username")String username){
        int count = psUserService.selectPsUserName(username);
        System.err.println("返回值测试："+count);
        if (count == 1){
            return "success";
        }else {
            return "error";
        }
           }

}
