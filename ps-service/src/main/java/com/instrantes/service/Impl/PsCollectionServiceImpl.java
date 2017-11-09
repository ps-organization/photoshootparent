package com.instrantes.service.Impl;
import com.instrantes.dao.PsCollectionDao;
import com.instrantes.pojo.PsCollection;
import com.instrantes.service.PsCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsCollectionServiceImpl implements PsCollectionService {
    @Autowired
    private PsCollectionDao psCollectionDao;

    //    根据用户id查询作品信息
    public List<PsCollection> selectPsCollectionByUserId (Integer id){
        return psCollectionDao.selectPsCollectionByUserId(id);
    }

    //查询所有作品信息
    public List<PsCollection> selectAllCollection(Integer likeUserId,Integer currentPicId,Integer picLoadNum){
        return psCollectionDao.selectAllCollection(likeUserId,currentPicId,picLoadNum);
    }
    //查询个人所有作品信息
    @Cacheable(value = "psCollection",key="'userId'+#userId")
    public List<PsCollection> selectCollectionInfoByUserId(Integer userId) {
        return psCollectionDao.selectCollectionInfoByUserId(userId);
    }

    //    批量上传多个图片
    @CacheEvict(value="psCollection",allEntries=true)
    public void batchInsertPsCollection(List<PsCollection> psCollectionList) {
        psCollectionDao.batchInsertPsCollection(psCollectionList);
    }
    public PsCollectionDao getPsCollectionDao() {
        return this.psCollectionDao;
    }
    public void setPsCollectionDao(PsCollectionDao psCollectionDao) {
        this.psCollectionDao = psCollectionDao;
    }

    public PsCollection selectSingleCollectionInfoByCollectionId(Integer collectionId){
        return psCollectionDao.selectSingleCollectionInfoByCollectionId(collectionId);
    }
}