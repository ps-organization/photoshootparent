package com.instrantes.dao;


import com.instrantes.pojo.PsUser;
import org.springframework.stereotype.Repository;

@Repository
public interface PsUserDao {

    //查询该用户是否存在
    PsUser selectPsUserByUserNameEmail(String username);

    PsUser selectPsUserById(Integer id);
    //查找用户所有信息*
    PsUser selectPsUserByName(String userName);

    int insertPsUser(PsUser value);

    //根据用户id查询作品信息
    PsUser selectPsCollectionByUserid(Integer userId);

    //    根据用户名只获取userId
    Integer selectPsUserUserIdByName(String userName);

    //修改密码
    int updateUserPassword(PsUser psUser);
}