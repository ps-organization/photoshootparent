package com.instrantes.service;
import com.instrantes.pojo.PsCollection;

import java.util.List;

public interface PsCollectionService{
    long getPsCollectionRowCount();
    PsCollection selectPsCollectionById(Integer id);
    void batchInsertPsCollection(List<PsCollection> psCollectionList);
    int insertPsCollection(PsCollection value);
    int insertNonEmptyPsCollection(PsCollection value);
    int deletePsCollectionById(Integer id);
    int updatePsCollectionById(PsCollection enti);
    int updateNonEmptyPsCollectionById(PsCollection enti);
    List<PsCollection> selectPsCollectionByUserId (Integer id);
}