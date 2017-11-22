package com.instrantes.service;

import com.instrantes.pojo.PsUser;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.cache.annotation.Cacheable;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public interface PsUserService {
//    String encryptPassword(PsUser psUser) ;

    Integer getCurrentPsUserId();

    PsUser selectPsUserName(String username);

    PsUser selectPsUserById(Integer id);

    PsUser selectPsUserGeneralInformationById(Integer id);//查询用户常用的信息，ID 昵称 用户头像 粉丝数目

    int insertPsUser(PsUser value);

    int insertPsUser(PsUser psUser, HttpServletRequest request);

    PsUser selectPsCollectionByUserid(Integer userId);

    PsUser selectPsUserByName(String userName);//查找用户所有信息*登录功能

    Integer selectPsUserUserIdByName(String userName);

    //用过昵称查询邮件
    PsUser selectPsByUserNameEmail(String username);

    int updateUserPassword(PsUser psUser);

    //发送修改密码的验证码
    void sendCode(String email);

    //判断验证是否正确，判断成功修改密码
    String isEmptyCode(Integer emailCodes,String uerName ,String password);
}