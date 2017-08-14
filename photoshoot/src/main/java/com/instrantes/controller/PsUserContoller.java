package com.instrantes.controller;

import com.instrantes.dao.PsRoleDao;
import com.instrantes.dao.PsUserDao;
import com.instrantes.pojo.PsRole;
import com.instrantes.pojo.PsUser;
import com.instrantes.service.PsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller

@RequestMapping("/PsUserController")
public class PsUserContoller {
    @Autowired
    private PsUserDao psUserDao;
    @Autowired
    private PsUserService psUserService;



    @RequestMapping(value = "/testuser", method = RequestMethod.GET)
    public String setForm() {
        return "testuser";
    }

    //    根据id查询PsUser
    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ResponseBody
    public PsUser selectPsUserById(Integer id) {
        PsUser psUser = psUserService.selectPsUserById(id);
        return psUser;
    }

    //    根据userName查询PsUser
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @ResponseBody
    public PsUser selectPsUserByName( String userName ) {
        PsUser psUser = psUserDao.selectPsUserByName(userName);
        System.out.println("--------------------------------------------------------");
        return psUser;
    }


    //    插入PsUser
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public int insertPsUser(String userName, String userPassword) {
        PsUser psUser = new PsUser();
        psUser.setUserName(userName);
        psUser.setUserPassword(userPassword);
//        判断插入是否成功
        int flag = psUserService.insertPsUser(psUser);
        return flag;
    }
}
