package com.instrantes.service.Impl;

import com.instrantes.dao.PsWatchDao;
import com.instrantes.pojo.PsWatch;
import com.instrantes.service.PsWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}