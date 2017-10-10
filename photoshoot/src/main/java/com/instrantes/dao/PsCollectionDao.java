package com.instrantes.dao;
import com.instrantes.pojo.PsCollection;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PsCollectionDao{
    long getPsCollectionRowCount();

    PsCollection selectPsCollectionById(Integer id);
//    批量上传多个图片
    void batchInsertPsCollection(@Param("psCollectionList") List<PsCollection> psCollectionList);

    int insertPsCollection(PsCollection value);
    int insertNonEmptyPsCollection(PsCollection value);
    int deletePsCollectionById(Integer id);
    int updatePsCollectionById(PsCollection enti);
    int updateNonEmptyPsCollectionById(PsCollection enti);

    List<PsCollection> selectPsCollectionByUserId ( Integer id);
}