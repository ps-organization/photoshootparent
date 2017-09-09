package com.instrantes.service.Impl;
import com.instrantes.dao.PsCollectionDao;
import com.instrantes.pojo.PsCollection;
import com.instrantes.service.PsCollectionService;

import java.util.List;

public class PsCollectionServiceImpl implements PsCollectionService {
    private PsCollectionDao psCollectionDao;
    @Override
    public long getPsCollectionRowCount(){
        return psCollectionDao.getPsCollectionRowCount();
    }
    @Override
    public List<PsCollection> selectPsCollection(){
        return psCollectionDao.selectPsCollection();
    }
    @Override
    public PsCollection selectPsCollectionById(Integer id){
        return psCollectionDao.selectPsCollectionById(id);
    }
    @Override
    public int insertPsCollection(PsCollection value){
        return psCollectionDao.insertPsCollection(value);
    }
    @Override
    public int insertNonEmptyPsCollection(PsCollection value){
        return psCollectionDao.insertNonEmptyPsCollection(value);
    }
    @Override
    public int deletePsCollectionById(Integer id){
        return psCollectionDao.deletePsCollectionById(id);
    }
    @Override
    public int updatePsCollectionById(PsCollection enti){
        return psCollectionDao.updatePsCollectionById(enti);
    }
    @Override
    public int updateNonEmptyPsCollectionById(PsCollection enti){
        return psCollectionDao.updateNonEmptyPsCollectionById(enti);
    }

    public PsCollectionDao getPsCollectionDao() {
        return this.psCollectionDao;
    }

    public void setPsCollectionDao(PsCollectionDao psCollectionDao) {
        this.psCollectionDao = psCollectionDao;
    }

}