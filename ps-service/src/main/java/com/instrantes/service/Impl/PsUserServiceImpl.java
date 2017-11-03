package com.instrantes.service.Impl;

import com.instrantes.dao.PsUserDao;
import com.instrantes.pojo.PsUser;
import com.instrantes.service.PsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    //    插入PsUser
    @Override
    public int insertPsUser(PsUser psUser) {
        return psUserDao.insertPsUser(psUser);
    }

    //    根据用户名只获取userId
    @Override
    public Integer selectPsUserUserIdByName(String userName) {
        return psUserDao.selectPsUserUserIdByName(userName);
}
}
