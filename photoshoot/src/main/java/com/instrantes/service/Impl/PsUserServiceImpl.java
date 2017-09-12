package com.instrantes.service.Impl;

import com.instrantes.dao.PsUserDao;
import com.instrantes.pojo.PsCollection;
import com.instrantes.pojo.PsUser;
import com.instrantes.service.PsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsUserServiceImpl implements PsUserService {
    @Autowired
    private PsUserDao psUserDao;

    @Override
    public PsUser selectPsUserById(Integer id) {
        PsUser psUser = psUserDao.selectPsUserById(id);
        return psUser;
    }

    @Override
    public PsUser selectPsCollectionByUserid(Integer userId) {
        PsUser psUser=psUserDao.selectPsCollectionByUserid(userId);
        return psUser;
    }

    public PsUser selectPsUserByName(String userName) {
        return psUserDao.selectPsUserByName(userName);
    }

    //    插入PsUser
    @Override
    public int insertPsUser(PsUser psUser) {
        return psUserDao.insertPsUser(psUser);
    }


    @Override
    public int insertNonEmptyPsUser(PsUser value) {
        return 0;
    }

    @Override
    public int deletePsUserById(Integer id) {
        return 0;
    }

    @Override
    public int updatePsUserById(PsUser enti) {
        return 0;
    }

    @Override
    public int updateNonEmptyPsUserById(PsUser enti) {
        return 0;
    }
}
