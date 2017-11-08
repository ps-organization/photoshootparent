package com.instrantes.service.Impl;

import com.instrantes.dao.PsUserDao;
import com.instrantes.pojo.PsUser;
import com.instrantes.service.PsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PsUserServiceImpl implements PsUserService {
    @Autowired
    private PsUserDao psUserDao;

    @Override
    public int selectPsUserName(String username) {
       int num =  psUserDao.selectPsUserByUserNameNotNull(username);
        return num;
    }

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
    //查找用户所有信息*登录功能
    @Cacheable(value = "user",key="'userName'+#userName")
    @Override
    public PsUser selectPsUserByName(String userName){
        return psUserDao.selectPsUserByName(userName);
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

    //查询该用户的邮箱是否匹配
    @Override
    public int selectPsUserEmail(String email) {
        return psUserDao.selectPsUserByEmail(email);
    }

    @Override
    public PsUser selectPsUserNameEmail(String username) {
        PsUser psUser = psUserDao.selectPsUserByNameEmail(username);
        return psUser;
    }
}
