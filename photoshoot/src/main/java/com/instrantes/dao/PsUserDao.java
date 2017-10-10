package com.instrantes.dao;


import com.instrantes.pojo.PsCollection;
import com.instrantes.pojo.PsUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PsUserDao {

    PsUser selectPsUserById(Integer id);

    PsUser selectPsUserByName(String userName);

    int insertPsUser(PsUser value);

    //根据用户id查询作品信息
    PsUser selectPsCollectionByUserid(Integer userId);

    //    根据用户名只获取userId
    int selectPsUserUserIdByName(String userName);

}