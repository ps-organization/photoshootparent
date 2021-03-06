package com.instrantes.controller;

import com.instrantes.pojo.PsUser;
import com.instrantes.pojo.PsWatch;
import com.instrantes.service.PsUserService;
import com.instrantes.service.PsWatchService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.LinkedHashMap;


@Controller
@RequestMapping("/PsUserController")
public class PsUserContoller{
    @Autowired
    private PsWatchService psWatchService;
    @Autowired
    private PsUserService psUserService;
    //此处是用户注册后登录的关键点
    @Autowired
    protected AuthenticationManager authenticationManager;

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
    //2017年11月14日注释，此处原本作为为处理登录过程，设置登录后需跳入controller的过程，但目前暂不需要设置该过程
//    @RequestMapping(value = "/initUser", method = RequestMethod.GET)
//    public String initialUserPage() {
//        return "user_default";
//    }

    //    用户登录功能,根据userName查询PsUser,2017年11月14日注释：发现已经在security中定义拦截
//    @RequestMapping(value = "/check", method = RequestMethod.GET)
//    @ResponseBody
//    public PsUser selectPsUserByName(String userName) {
//        PsUser psUser = psUserService.selectPsUserByName(userName);
//        return psUser;
//    }

    //用户登录功能，显示用户信息，此处实际属于登陆状态的一个判断，如果没找到，则提醒需要登录
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @ResponseBody
    public PsUser selectPsUserByName() {
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        PsUser psUser = psUserService.selectPsUserByName(authentication.getName());
        return psUser;
    }
    //用户退出功能,已在security中配置了，所以不用
//    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//        /**
//        *用户退出功能，验证授权是否存在
//        *@param [request, response]
//        *@return java.lang.String
//        *@date 2017/10/23
//        */
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/templates/photoshoot_default.html";
//    }

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
        //返回插入是否成功1/0
        return  psUserService.insertPsUser(psUser,request);
    }

    /**
     * 验证用户是否存在
     * @param username
     * @return
     */
    @RequestMapping(value = "/resetuserpw", method = RequestMethod.POST)
    @ResponseBody
    public String resetUserPw(@RequestParam("username") String username) {
        PsUser psUser = psUserService.selectPsByUserNameEmail(username);
        if (psUser.getUserEmail() != null) {
            return "success";
        } else {
            return "该用户未绑定邮箱";
        }
    }

    /**
     * 验证邮箱是否属于用户
     * 通过验证后发送验证码到用户邮箱
     * 邮箱通过用户绑定的邮箱发送，不从页面获取的邮箱发送邮件
     *
     * @param email
     * @param username
     * @return
     * @throws MessagingException
     * @throws GeneralSecurityException
     * @throws javax.mail.MessagingException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/sendEmailCode", method = RequestMethod.POST)
    @ResponseBody
    public String sendEmailCode(@RequestParam("email") String email, @RequestParam("username") String username) throws MessagingException, GeneralSecurityException, javax.mail.MessagingException, UnsupportedEncodingException {
        //判断从页面传来的邮箱是否和数据库的匹配
        PsUser psUser = psUserService.selectPsByUserNameEmail(username);
        if (psUser.getUserEmail().equals(email)) {
            psUserService.sendCode(email); //发送邮箱
            return "success";
        } else {
            return "error";
        }
    }

    //修改密码
    @RequestMapping(value = "/EmailCodeResetPw", method = RequestMethod.POST)
    @ResponseBody
    public String EmailCodeResetPw(@RequestBody LinkedHashMap<String, String> psUserJson) {
        return psUserService.isEmptyCode(Integer.parseInt(psUserJson.get("emailcode")),psUserJson.get("userName"),psUserJson.get("userPassword"));
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
