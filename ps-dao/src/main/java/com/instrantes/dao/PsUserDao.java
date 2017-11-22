package com.instrantes.dao;


import com.instrantes.pojo.PsUser;
import org.springframework.stereotype.Repository;

@Repository
public interface PsUserDao {


    PsUser selectPsUserByUserNameEmail(String username);

    PsUser selectPsUserById(Integer id);    //查询该用户是否存在

    PsUser selectPsUserByName(String userName);    //查找用户所有信息*

    PsUser selectPsUserGeneralInformationById(Integer id);//查询用户常用的信息，ID 昵称 用户头像 粉丝数目

    int insertPsUser(PsUser value);

    PsUser selectPsCollectionByUserid(Integer userId);    //根据用户id查询作品信息

    Integer selectPsUserUserIdByName(String userName);    //    根据用户名只获取userId

    int updateUserPassword(PsUser psUser);    //修改密码
}