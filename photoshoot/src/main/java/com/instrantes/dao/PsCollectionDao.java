package com.instrantes.dao;
import com.instrantes.pojo.PsCollection;

import java.util.List;
public interface PsCollectionDao{
//    long getPsCollectionRowCount();
    //根据用户id查询作品信息
    List<PsCollection> selectPsCollectionByUserid(Integer id);
//    PsCollection selectPsCollectionById(Integer id);
//    int insertPsCollection(PsCollection value);
//    int insertNonEmptyPsCollection(PsCollection value);
//    int deletePsCollectionById(Integer id);
//    int updatePsCollectionById(PsCollection enti);
//    int updateNonEmptyPsCollectionById(PsCollection enti);
}