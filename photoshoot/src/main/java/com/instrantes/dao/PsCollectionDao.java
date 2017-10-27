package com.instrantes.dao;
import com.instrantes.pojo.PsCollection;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PsCollectionDao{
//    批量上传多个图片
    void batchInsertPsCollection(@Param("psCollectionList") List<PsCollection> psCollectionList);
    List<PsCollection> selectAllCollection(Integer likeUserId);    //查询所有作品信息
    List<PsCollection> selectPsCollectionByUserId ( Integer id);
    List<PsCollection> selectCollectionInfoByUserId( Integer id);    //查询所有作品信息
    PsCollection selectSingleCollectionInfoByCollectionId(Integer collectionId);
}