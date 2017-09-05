package com.instrantes.controller;

import com.instrantes.dao.PsUserDao;
import com.instrantes.dao.PsWatchDao;
import com.instrantes.pojo.PsUser;
import com.instrantes.pojo.PsWatch;
import com.instrantes.service.PsUserService;
import com.instrantes.service.PsWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller

@RequestMapping("/PsUserController")
public class PsUserContoller {
    @Autowired
    private PsWatchService psWatchService;
    @Autowired
    private PsUserDao psUserDao;
    @Autowired
    private PsUserService psUserService;


    @RequestMapping(value = "/testuser", method = RequestMethod.GET)
    public String setForm() {
        return "testuser";
    }

    @RequestMapping(value = "/initUser", method = RequestMethod.GET)
    public String initialUserPage() {
        return "user_default";
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
    public PsUser selectPsUserByName(String userName) {
        PsUser psUser = psUserDao.selectPsUserByName(userName);
        return psUser;
    }

    //登录后，显示用户信息
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @ResponseBody
    public PsUser selectPsUserByName() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUsername());
        PsUser psUser = psUserDao.selectPsUserByName(userDetails.getUsername());
        return psUser;
    }
    //登录后,显示用户粉丝数量
    @RequestMapping(value = "/showFansCount", method = RequestMethod.GET)
    @ResponseBody
    public PsWatch getPsWatchFansCountByPsUserId(int id) {
        PsWatch psWatch = psWatchService.getPsWatchFansCountByPsUserId(id);
        return psWatch;
    }
    //登录后,显示用户关注数量
    @RequestMapping(value = "/showWatchUserCount", method = RequestMethod.GET)
    @ResponseBody
    public PsWatch getPsWatchUserCountByPsUserId(int id) {
        PsWatch psWatch = psWatchService.getPsWatchUserCountByPsUserId(id);
        return psWatch;
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
