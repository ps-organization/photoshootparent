package com.instrantes.service;

import com.instrantes.pojo.PsCollection;
import com.instrantes.pojo.PsUser;

import java.util.List;

public interface PsUserService {

    PsUser selectPsUserById(Integer id);

    int insertPsUser(PsUser value);

    PsUser selectPsCollectionByUserid(Integer userId);

    int selectPsUserUserIdByName(String userName);
}