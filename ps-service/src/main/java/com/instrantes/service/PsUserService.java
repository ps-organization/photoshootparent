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

    int selectPsUserName(String username);

    PsUser selectPsUserById(Integer id);

    int insertPsUser(PsUser value);

    int insertPsUser(PsUser psUser, HttpServletRequest request);

    PsUser selectPsCollectionByUserid(Integer userId);

    PsUser selectPsUserByName(String userName);//查找用户所有信息*登录功能

    Integer selectPsUserUserIdByName(String userName);

    int selectPsUserEmail(String email);

    PsUser selectPsUserNameEmail(String username);

    int updateUserPassword(PsUser psUser);

    //发送修改密码的验证码
    void sendCode(String email);

    //判断验证是否正确，判断成功修改密码
    String isEmptyCode(Integer emailCodes,String uerName ,String password);
}