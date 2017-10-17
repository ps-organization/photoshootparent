package com.instrantes.dao;

import com.instrantes.pojo.PsLike;
import org.springframework.stereotype.Repository;

@Repository
public interface PsLikeDao{

//    以下是赞功能
    int selectCountLikeByCollectionId(Integer collectionId);//    显示单个作品的总赞数
    PsLike showLikeStatus(Integer likeUserid, Integer collectionId);//    1.显示自己是否赞,若无当前用户赞的相关记录,insertPsLike
    void insertPsLike(Integer likeStatus,Integer likeUserid);//    2.插入当前用户的空白赞记录，用getter获取记录id
    void updateSingleCollectionLikeStatus(Integer likeId,Integer likeStatus);//    点赞/消赞

//    long getPsLikeRowCount();
//    List<PsLike> selectPsLike();
//    PsLike selectPsLikeById(Integer id);
//    int insertPsLike(PsLike value);
//    int insertNonEmptyPsLike(PsLike value);
//    int deletePsLikeById(Integer id);
//    int updatePsLikeById(PsLike enti);
//    int updateNonEmptyPsLikeById(PsLike enti);
}