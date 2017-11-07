package com.instrantes.service;

import com.instrantes.pojo.PsUser;
import org.springframework.cache.annotation.Cacheable;

public interface PsUserService {

    int selectPsUserName(String username);

    PsUser selectPsUserById(Integer id);

    int insertPsUser(PsUser value);

    PsUser selectPsCollectionByUserid(Integer userId);

    PsUser selectPsUserByName(String userName);//查找用户所有信息*登录功能

    Integer selectPsUserUserIdByName(String userName);
}