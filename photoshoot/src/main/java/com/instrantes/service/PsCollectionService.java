package com.instrantes.service;
import com.instrantes.pojo.PsCollection;

import java.util.List;

public interface PsCollectionService{
//    long getPsCollectionRowCount();
//    PsCollection selectPsCollectionById(Integer id);
    void batchInsertPsCollection(List<PsCollection> psCollectionList);
    //查询所有作品信息
    List<PsCollection> selectAllCollection(Integer likeUserId);
//    int insertPsCollection(PsCollection value);
//    int insertNonEmptyPsCollection(PsCollection value);
//    int deletePsCollectionById(Integer id);
//    int updatePsCollectionById(PsCollection enti);
//    int updateNonEmptyPsCollectionById(PsCollection enti);
    List<PsCollection> selectPsCollectionByUserId (Integer id);
    //查询个人所有作品信息
    List<PsCollection> selectCollectionInfoByUserId(Integer userId);
}