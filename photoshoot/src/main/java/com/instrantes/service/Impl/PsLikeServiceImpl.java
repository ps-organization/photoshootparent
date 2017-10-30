package com.instrantes.service.Impl;
import com.instrantes.dao.PsLikeDao;
import com.instrantes.service.PsLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PsLikeServiceImpl implements PsLikeService{
    @Autowired
    private PsLikeDao psLikeDao;

    public PsLikeDao getPsLikeDao() {
        return this.psLikeDao;
    }

    public void setPsLikeDao(PsLikeDao psLikeDao) {
        this.psLikeDao = psLikeDao;
    }

    public void insertLikeRecordByUserId(Integer likeCollectionid,Integer likeUserid){
        psLikeDao.insertLikeRecordByUserId(likeCollectionid,likeUserid);
    }
}