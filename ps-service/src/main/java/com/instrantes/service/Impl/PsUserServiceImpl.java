package com.instrantes.service.Impl;

import com.instrantes.Utils.SendEmailUtils;
import com.instrantes.dao.PsUserDao;
import com.instrantes.pojo.PsUser;
import com.instrantes.service.PsUserService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Random;

@Service
public class PsUserServiceImpl implements PsUserService {
    @Autowired
    private PsUserDao psUserDao;
    //此处是用户注册后登录的关键点
    @Autowired
    protected AuthenticationManager authenticationManager;
    //创建一个4位数的数字验证码
    Integer emailCode;

    private String encryptPassword(PsUser psUser) {
        String password;
        String oldPassword = psUser.getUserPassword();
        password = new BCryptPasswordEncoder().encode(oldPassword);
        psUser.setUserPassword(password);
        return oldPassword;
    }

    /**
     *根据授权的name获取用户id(获取当前登陆用户id的方法)
     *@param
     *@return java.lang.Integer
     *Date 2017/11/8
     */
    @Override
    public Integer getCurrentPsUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return psUserDao.selectPsUserUserIdByName(authentication.getName());
    }

    @Override
    public PsUser selectPsUserName(String username) {
        return  psUserDao.selectPsUserByUserNameEmail(username);
    }

    @Override
    public PsUser selectPsUserById(Integer id) {
        PsUser psUser = psUserDao.selectPsUserById(id);
        return psUser;
    }

    @Override
    public PsUser selectPsCollectionByUserid(Integer userId) {
        PsUser psUser = psUserDao.selectPsCollectionByUserid(userId);
        return psUser;
    }

    //查找用户所有信息*登录功能
    @Cacheable(value = "user", key = "'userName'+#userName")
    @Override
    public PsUser selectPsUserByName(String userName) {
        return psUserDao.selectPsUserByName(userName);
    }


    //    插入PsUser
    public int insertPsUser(PsUser value){
        return psUserDao.insertPsUser(value);
    }

    //    新插入PsUser
    @Override
    public int insertPsUser(PsUser psUser, HttpServletRequest request) {
//        加密用户密码，并返回旧密码
        String oldPassword=encryptPassword(psUser);
//        判断插入是否成功
        int flag = psUserDao.insertPsUser(psUser);
        //此处用来注册后的自动登录,增加该注册用户的验证和授权
        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(psUser.getUserName(),oldPassword);
        request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        return flag;
    }

    //    根据用户名只获取userId
    @Override
    public Integer selectPsUserUserIdByName(String userName) {
        return psUserDao.selectPsUserUserIdByName(userName);
    }


    //获取用户邮箱
    @Override
    public PsUser selectPsByUserNameEmail(String username) {
        PsUser psUser = psUserDao.selectPsUserByUserNameEmail(username);
        return psUser;
    }

    //修改密码
    @Override
    public int updateUserPassword(PsUser psUser) {
        int count = psUserDao.updateUserPassword(psUser);
        return count;
    }

    //发送带有验证码的邮件
    @Override
    public void sendCode(String email) {
        //创建邮箱主题
        String emailTheme = "影约密码找回";
        //创建一个四位数的验证码
        emailCode = Integer.parseInt(String.valueOf(new Random().nextInt(8999) + 1000));
        //创建邮件发送文本格式
        String emailText = "<h2>亲爱的用户您好，您正在进行修改密码操作，请确认为本人操作！请在输入框中输入：<a style='font-size :45px ;color: #d58512 '>" + emailCode + "</a>&nbsp;以完成下一步操作。</h2></br><a style='color: darkgrey'>注意：此操作可能会修改您的密码、登录邮箱或绑定手机。如非本人操作，请及时登录并修改密码以保证帐户安全 \n" +
                "（工作人员不会向你索取此验证码，请勿泄漏！)</a></h5><hr size='0.01' color='darkgrey'><p>此为系统邮件，请勿回复<br>\n" +
                "                        请保管好您的邮箱，避免账号被他人盗用\n" +
                "                    </p>";
        //发送邮箱工具类
        SendEmailUtils sendEmailUtils = new SendEmailUtils();
        try {
            //发送邮箱，email:用户邮箱；emailTheme：邮箱主题；emailText：邮箱文本
            sendEmailUtils.sendEmail(email, emailTheme, emailText);
        } catch (UnsupportedEncodingException | MessagingException | GeneralSecurityException | javax.mail.MessagingException e ) {
            e.printStackTrace();
        }
    }

    //进行验证码判断并修改密码
    @Override
    public String isEmptyCode(Integer emailCodes, String userName, String password) {
        int count;
        if (emailCode.equals(emailCodes)) {
            PsUser psUser = new PsUser();
            psUser.setUserPassword(password);
            encryptPassword(psUser);
            psUser.setUserName(userName);
            count = updateUserPassword(psUser);
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

