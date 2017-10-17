package com.instrantes.service.Impl;
import com.instrantes.dao.PsCollectionDao;
import com.instrantes.pojo.PsCollection;
import com.instrantes.service.PsCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PsCollectionServiceImpl implements PsCollectionService {
    @Autowired
    private PsCollectionDao psCollectionDao;

//    public long getPsCollectionRowCount(){
//        return psCollectionDao.getPsCollectionRowCount();
//    }
//    @Override
//    public PsCollection selectPsCollectionById(Integer id){
//        return psCollectionDao.selectPsCollectionById(id);
//    }
//    @Override
//    public int insertPsCollection(PsCollection value){
//        return psCollectionDao.insertPsCollection(value);
//    }
//    @Override
//    public int insertNonEmptyPsCollection(PsCollection value){
//        return psCollectionDao.insertNonEmptyPsCollection(value);
//    }
//    @Override
//    public int deletePsCollectionById(Integer id){
//        return psCollectionDao.deletePsCollectionById(id);
//    }
//    @Override
//    public int updatePsCollectionById(PsCollection enti){
//        return psCollectionDao.updatePsCollectionById(enti);
//    }

//    public int updateNonEmptyPsCollectionById(PsCollection enti){
//        return psCollectionDao.updateNonEmptyPsCollectionById(enti);
//    }
    //    根据用户id查询作品信息
    public List<PsCollection> selectPsCollectionByUserId (Integer id){
        return psCollectionDao.selectPsCollectionByUserId(id);
    }
    //    批量上传多个图片
    @Override
    public void batchInsertPsCollection(List<PsCollection> psCollectionList) {
         psCollectionDao.batchInsertPsCollection(psCollectionList);
    }
    //查询所有作品信息
    public List<PsCollection> selectAllCollection(){
        return psCollectionDao.selectAllCollection();
    }
    //查询个人所有作品信息
    public List<PsCollection> selectCollectionInfoByUserId(Integer userId) {
        return psCollectionDao.selectCollectionInfoByUserId(userId);
    }
    public PsCollectionDao getPsCollectionDao() {
        return this.psCollectionDao;
    }
    public void setPsCollectionDao(PsCollectionDao psCollectionDao) {
        this.psCollectionDao = psCollectionDao;
    }

}