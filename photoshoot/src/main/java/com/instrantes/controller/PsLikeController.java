package com.instrantes.controller;

import com.instrantes.service.PsLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Lime on 2017/10/30
 */
@Controller
@RequestMapping("/Like")
public class PsLikeController {
    @Autowired
    PsLikeService psLikeService;

    @RequestMapping(value = "/Like",method = RequestMethod.POST)
    /**
    *当用户点赞时，触发该功能，插入一条点赞记录
    *@param [likeCollectionid, likeUserid]
    *@return void
    *@date 2017/10/30
    */
    @ResponseBody
    public void insertLikeRecordByUserId(Integer likeCollectionid, Integer likeUserid) {
        System.out.println("likeCollection:"+likeCollectionid);
        System.out.println("likeUserid:"+likeUserid);
        psLikeService.insertLikeRecordByUserId(likeCollectionid, likeUserid);
    }
}
