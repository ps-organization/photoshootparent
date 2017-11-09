package com.instrantes.controller;

import com.instrantes.Utils.SendEmailUtils;
import com.instrantes.pojo.PsUser;
import com.instrantes.service.PsUserService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Random;

@Controller
@RequestMapping(value = "/resetpw")
public class PsResetUserPwController {

    @Autowired
    PsUserService psUserService;

    //创建一个4位数的数字验证码
    Integer emailCode;

    /**
     * 验证用户是否存在
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/resetuserpw", method = RequestMethod.POST)
    @ResponseBody
    public Boolean resetUserPw(@RequestParam("username") String username) {
        int count = psUserService.selectPsUserName(username);
        System.err.println("用户名返回值测试：" + count + "用户：" + username);
        if (count == 1) {

            return true;
        } else {
            return false;
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
        int count = psUserService.selectPsUserEmail(email);
        System.err.println("邮箱返回值测试：" + count);
        if (count == 1) {
            PsUser psUser = psUserService.selectPsUserNameEmail(username);
            //创建邮箱主题
            String emailTheme = "影约密码找回";
            //...
            emailCode = Integer.parseInt(String.valueOf(new Random().nextInt(8999) + 1000));
            //创建邮件发送文本格式
            String emailText = "<h2>亲爱的用户您好，您正在进行修改密码操作，请确认为本人操作！请在输入框中输入：<a style='font-size :45px ;color: #d58512 '>" + emailCode + "</a>&nbsp;以完成下一步操作。</h2></br><a style='color: darkgrey'>注意：此操作可能会修改您的密码、登录邮箱或绑定手机。如非本人操作，请及时登录并修改密码以保证帐户安全 \n" +
                    "（工作人员不会向你索取此验证码，请勿泄漏！)</a></h5><hr size='0.01' color='darkgrey'><p>此为系统邮件，请勿回复<br>\n" +
                    "                        请保管好您的邮箱，避免账号被他人盗用\n" +
                    "                    </p>";
            SendEmailUtils sendEmailUtils = new SendEmailUtils();
            sendEmailUtils.sendEmail(psUser.getUserEmail(), emailTheme, emailText);
            return "success";
        } else {
            return "error";
        }
    }

    //修改密码
    @RequestMapping(value = "/EmailCodeResetPw", method = RequestMethod.POST)
    @ResponseBody
    public String EmailCodeResetPw(@RequestParam("emailcode") Integer emailcode, @RequestParam("userName") String username, @RequestParam("userPassword") String userpassword) {
        int count;
        if (emailCode.equals(emailcode)) {
            PsUser psUser = new PsUser();
            psUser.setUserPassword(userpassword);
            psUserService.encryptPassword(psUser);
//            String password = new BCryptPasswordEncoder().encode(userpassword);
            psUser.setUserName(username);
            count = psUserService.updateUserPassword(psUser);
            if (count == 1) {
                emailCode = null;
                return "update success";
            } else {
                return "update error";
            }
        }

        return "";
    }
}
