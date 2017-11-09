package com.instrantes.controller;

import com.instrantes.pojo.PsUser;
import com.instrantes.pojo.PsWatch;
import com.instrantes.service.PsUserService;
import com.instrantes.service.PsWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/PsUserController")
public class PsUserContoller {
    @Autowired
    private PsWatchService psWatchService;
    @Autowired
    private PsUserService psUserService;
    //此处是用户注册后登录的关键点
    @Autowired
    protected AuthenticationManager authenticationManager;

//    加密方法，用于对注册时用户密码加密,并返回原本的密码
    private String encryptPassword(PsUser psUser) {
        String password ;
        String oldPassword = psUser.getUserPassword() ;
        password = new BCryptPasswordEncoder().encode(oldPassword);
        psUser.setUserPassword(password);
        return oldPassword;
    }
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
        PsUser psUser = psUserService.selectPsUserByName(userName);
        return psUser;
    }

    //用户登录功能，显示用户信息，此处实际属于登陆状态的一个判断，如果没找到，则提醒需要登录
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @ResponseBody
    public PsUser selectPsUserByName() {
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        PsUser psUser = psUserService.selectPsUserByName(authentication.getName());
        return psUser;
    }
    //用户退出功能
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        /**
        *用户退出功能，验证授权是否存在
        *@param [request, response]
        *@return java.lang.String
        *@date 2017/10/23
        */
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/templates/user_default.html";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
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

    //get方式，通过id来查询watch以及fan用户部分信息
    @RequestMapping(value = "/psUser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PsUser getPsWatchDetail(@PathVariable("id")Integer id) {
        PsUser psUser=psUserService.selectPsUserById(id);
        System.out.println(psUser.getUserName());
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

    //新插入PsUser
    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    @ResponseBody
    public int newPsUser(@RequestBody PsUser psUser, HttpServletRequest request) {

//        加密用户密码
        String oldPassword=encryptPassword(psUser);
//        判断插入是否成功
        int flag = psUserService.insertPsUser(psUser);
        System.out.println("已创建用户:"+psUser.getUserName());
        //此处用来注册后的自动登录,增加该注册用户的验证和授权
        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(psUser.getUserName(),oldPassword);
        request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        return flag;
    }

//    //根据用户id查询作品的详情
//    @RequestMapping(value = "/userCollections", method = RequestMethod.POST)
//    @ResponseBody
//    public PsUser selectPsCollectionByUserid(Integer userId) {
//        PsUser psUser=psUserService.selectPsCollectionByUserid(1);
//        System.out.println(psUser);
//        return psUser;
//    }

    //根据用户id查询关注id
//    @RequestMapping(value = "/userCollections", method = RequestMethod.POST)
//    @ResponseBody
//    public PsUser selectPsWatchIdByUserId(Integer userId) {
//        PsUser psUser=psUserService.selectPsCollectionByUserid(1);
//        System.out.println(psUser);
//        return psUser;
//    }
    //根据用户id查询粉丝id
}
