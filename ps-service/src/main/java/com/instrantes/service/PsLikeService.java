package com.instrantes.service;

import com.instrantes.pojo.PsLike;

public interface PsLikeService{
    PsLike selectStatus(Integer collectionId , Integer userId);//查询likeStatus，传入参数：collectionID,userID
    public void insertLikeRecordByUserId(Integer likeCollectionid, Integer likeUserid); //插入点赞记录，
}