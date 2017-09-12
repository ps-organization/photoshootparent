package com.instrantes.service;

import com.instrantes.pojo.PsCollection;
import com.instrantes.pojo.PsUser;

import java.util.List;

public interface PsUserService {
//    long getPsUserRowCount(Assist assist);

//    List<PsUser> selectPsUser(Assist assist);

    PsUser selectPsUserById(Integer id);

    PsUser selectPsUserByName(String userName) ;

    int insertPsUser(PsUser value);

    PsUser selectPsCollectionByUserid(Integer userId);

    int insertNonEmptyPsUser(PsUser value);

    int deletePsUserById(Integer id);

//    int deletePsUser(Assist assist);

    int updatePsUserById(PsUser enti);

//    int updatePsUser(PsUser value, Assist assist);

    int updateNonEmptyPsUserById(PsUser enti);

//    int updateNonEmptyPsUser(PsUser value, Assist assist);
}