package com.instrantes.service;
import com.instrantes.pojo.PsCollection;

import java.util.List;

public interface PsCollectionService{
    long getPsCollectionRowCount();
    List<PsCollection> selectPsCollection();
    PsCollection selectPsCollectionById(Integer id);
    int insertPsCollection(PsCollection value);
    int insertNonEmptyPsCollection(PsCollection value);
    int deletePsCollectionById(Integer id);
    int updatePsCollectionById(PsCollection enti);
    int updateNonEmptyPsCollectionById(PsCollection enti);
}