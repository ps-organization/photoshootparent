package com.instrantes.dao;


import com.instrantes.pojo.PsCollection;
import com.instrantes.pojo.PsUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PsUserDao {
//    long getPsUserRowCount(Assist assist);

//    List<PsUser> selectPsUser(Assist assist);

    PsUser selectPsUserById(Integer id);

    PsUser selectPsUserByName(String userName);

    int insertPsUser(PsUser value);

    //根据用户id查询作品信息
    PsUser selectPsCollectionByUserid(Integer userId);

    int insertNonEmptyPsUser(PsUser value);

    int deletePsUserById(Integer id);

//    int deletePsUser(Assist assist);

    int updatePsUserById(PsUser enti);

//    int updatePsUser(@Param("enti") PsUser value, @Param("assist") Assist assist);

    int updateNonEmptyPsUserById(PsUser enti);

//    int updateNonEmptyPsUser(@Param("enti") PsUser value, @Param("assist") Assist assist);
}