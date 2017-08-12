package com.instrantes.controller;

import com.instrantes.dao.PsRoleDao;
import com.instrantes.pojo.PsRole;
import com.instrantes.pojo.PsUser;
import com.instrantes.service.PsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller

@RequestMapping("/PsUserController")
public class PsUserContoller {
    @Autowired
    private PsUserService psUserService;
    @Autowired
    private PsRoleDao psRoleDao;


    @RequestMapping(value = "/testuser", method = RequestMethod.GET)
    public String setForm() {
        return "testuser";
    }

    //    根据id查询PsUser
    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ResponseBody
    public PsUser selectPsUserById(Integer id) {
        PsUser psUser = psUserService.selectPsUserById(id);
//    测试部分
//        PsRole psRole=psRoleDao.selectUserRoleById(1);
//        System.out.println(psRole.getPsUser().getUserName());
//        System.out.println(psRole.getPsUser().getUserPassword());
//        System.out.println(psRole.getRoleName());
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
