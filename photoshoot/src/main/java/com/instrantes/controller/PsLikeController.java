package com.instrantes.controller;

import com.instrantes.service.PsLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Lime on 2017/10/30
 */
@Controller
@RequestMapping("/like")
public class PsLikeController {
    @Autowired
    PsLikeService psLikeService;

    @RequestMapping(value = "/likeRecord", method = RequestMethod.POST)
    /**
    *当用户点赞时，触发该功能，插入一条点赞记录
    *@param [likeJson]
    *@return void
    *@date 2017/10/30
    */
    @ResponseBody
    public void insertLikeRecordByUserId(@RequestBody Map<String, Integer> likeJson) {
//                Map<String,String>map = JSON.parseObject(likeJson,new TypeReference<Map<String, String>>(){});
        System.out.println("likeCollection:" + likeJson.get("likeCollectionid"));
        psLikeService.insertLikeRecordByUserId(likeJson.get("likeCollectionid"), likeJson.get("likeUserid"));
    }
}
