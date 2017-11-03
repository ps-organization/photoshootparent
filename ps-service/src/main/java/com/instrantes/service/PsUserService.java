package com.instrantes.service;

import com.instrantes.pojo.PsUser;

public interface PsUserService {

    PsUser selectPsUserById(Integer id);

    int insertPsUser(PsUser value);

    PsUser selectPsCollectionByUserid(Integer userId);

    Integer selectPsUserUserIdByName(String userName);
}