package com.instrantes.controller;

import com.instrantes.service.PsLikeService;
import com.instrantes.service.PsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Lime on 2017/10/30
 */
@Controller
@RequestMapping("/like")
public class PsLikeController {
//    static Logger logger = LogManager.getLogger(PsLikeController.class.getName());
    @Autowired
    PsLikeService psLikeService;
    @Autowired
    PsUserService psUserService;

    @RequestMapping(value = "/likeRecord", method = RequestMethod.POST)
    /**
     *当用户点赞时，触发该功能，插入一条点赞记录
     *@param [likeCollectionID]
     *@return void
     *@date 2017/11/8
     */
    @ResponseBody
    public Integer insertLikeRecordByUserId(@RequestBody Map<String, Integer> likeJson) {
//                Map<String,String>map = JSON.parseObject(likeJson,new TypeReference<Map<String, String>>(){});
        psLikeService.insertLikeRecordByUserId(likeJson.get("likeCollectionid"), psUserService.getCurrentPsUserId());
        return 1;
    }
}
