package com.instrantes.service.Impl;

import com.instrantes.dao.PsWatchDao;
import com.instrantes.pojo.PsWatch;
import com.instrantes.service.PsWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsWatchServiceImpl implements PsWatchService {
    @Autowired
    private PsWatchDao psWatchDao;

    public PsWatch getPsWatchFansCountByPsUserId(int id) {
        return psWatchDao.getPsWatchFansCountByPsUserId(id);
    }

    @Override
    public PsWatch getPsWatchUserCountByPsUserId(int id) {
        return psWatchDao.getPsWatchUserCountByPsUserId(id);
    }

    public PsWatchDao getPsWatchDao() {
        return this.psWatchDao;
    }

    public void setPsWatchDao(PsWatchDao psWatchDao) {
        this.psWatchDao = psWatchDao;
    }

    //通过当前用户id,获取所有粉丝头像姓名简略信息
    public List<PsWatch> getPsWatchFans(Integer id){
        return psWatchDao.getPsWatchFans(id);

    }
    //通过当前用户id,获取所有关注头像姓名简略信息
    public List<PsWatch> getPsWatchUser(Integer id){
        return psWatchDao.getPsWatchUser(id);
    }
}