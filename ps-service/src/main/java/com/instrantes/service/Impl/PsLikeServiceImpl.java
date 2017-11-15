package com.instrantes.service.Impl;
import com.instrantes.dao.PsLikeDao;
import com.instrantes.service.PsLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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


@PreAuthorize("hasAuthority('ROLE_ORDIN')")
/**
 *用户点赞后插入一条点赞记录，该方法被保护，用于只有有ROLE_ORDIN权限的用户才能进行调用
 *@param [likeCollectionid, likeUserid]
 *@return void
 *Date: 2017/11/15
 */
    public void insertLikeRecordByUserId(Integer likeCollectionid,Integer likeUserid){
        psLikeDao.insertLikeRecordByUserId(likeCollectionid,likeUserid);
    }
}