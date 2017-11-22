package com.instrantes.controller;

import com.alibaba.fastjson.JSONObject;
import com.instrantes.base.ApplicationException;
import com.instrantes.pojo.PsCollection;
import com.instrantes.service.PsCollectionService;
import com.instrantes.service.PsLikeService;
import com.instrantes.service.PsUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/collection")
public class PsCollectionController {
    static Logger logger = LogManager.getLogger(PsCollectionController.class);

    @Autowired
    PsCollectionService psCollectionService;
    @Autowired
    PsUserService psUserService;
    @Autowired
    PsLikeService psLikeService;

    /**
     *此处需要加入验证，验证是否找到用户ID
     *@param
     *@return int
     *Date 2017/10/25
     */
    //    此处为获取当前用户id的方法
    protected Integer getCurrentPsUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return psUserService.selectPsUserUserIdByName(authentication.getName());
    }

    //根据用户id查询作品的详情
    @RequestMapping(value = "/userCollections", method = RequestMethod.POST)
    @ResponseBody
    public List<PsCollection> selectPsCollectionByUserId(Integer id) {
        return  psCollectionService.selectPsCollectionByUserId(id);
    }
     /**
     *批量上传多个图片
      * @param hashMap key有两个picArray（大图）smallPicArray（小图）
     *@return void
     *Date: 2017/11/21
     */
    @RequestMapping(value = "/publishPic", method = RequestMethod.POST)
    @ResponseBody
    public void batchInsertPsCollection(@RequestBody HashMap<String,ArrayList<String>> hashMap) {
        int currentUserId = psUserService.getCurrentPsUserId();
        psCollectionService.batchInsertPsCollection(hashMap,currentUserId);
    }

    /**
     *此处应该加入随机显示功能，或者显示最新的
     * 已经实现（大数据库分段查询功能），即每次仅查询一部分图片。而不是整个数据库的所有图片
     *@param currentPicId 图片当前ID
     *@param picLoadNum 图片数目
     *@return java.util.List<com.instrantes.pojo.PsCollection>
     *Date 2017/11/3
     */
    //查询所有作品信息
    @RequestMapping(value = "/allCollection", method = RequestMethod.POST)
    @ResponseBody
    public List<PsCollection> selectAllCollection(Integer currentPicId, Integer picLoadNum) {
//        throw new ApplicationException("sss", 10001);
        if (getCurrentPsUserId() == null) {
            System.out.println("---------------------------------:" + getCurrentPsUserId());
            return psCollectionService.selectAllCollection(0, currentPicId, picLoadNum);
        }
        return psCollectionService.selectAllCollection(getCurrentPsUserId(), currentPicId, picLoadNum);
    }
    /**
     *根据登陆用户的ID，获取当前登陆用户的所有作品
     *@param
     *@return java.util.List<com.instrantes.pojo.PsCollection>
     *Date 2017/11/13
     */
    //查询个人所有作品信息。此处用了json，因为前端传入的是Json字符串
    @RequestMapping(value = "/personCollection", method = RequestMethod.POST)
    @ResponseBody
    public List<PsCollection> selectCollectionInfoByUserId() { // 该方法不能用Integer接收

        return psCollectionService.selectCollectionInfoByUserId(psUserService.getCurrentPsUserId());
    }
    //查询其他人所有作品信息。此处用了json，因为前端传入的是Json字符串，类似上面那个方法
//    @RequestMapping(value = "/personCollection", method = RequestMethod.POST)
//    @ResponseBody
//    public List<PsCollection> selectCollectionInfoByUserId(@RequestBody String userId) { // 该方法不能用Integer接收
//        /**
//         *根据用户的ID，获取个人用户的所有作品
//         *@param [userId]
//         *@return java.util.List<com.instrantes.pojo.PsCollection>
//         *@date 2017/10/21
//         */
//        JSONObject array = JSONObject.parseObject(userId);
//        System.out.println("----------------id:" + array.getInteger("userId"));
//        return psCollectionService.selectCollectionInfoByUserId(array.getInteger("userId"));
//    }


    //单个作品信息
    @RequestMapping(value = "/singleColletion", method = RequestMethod.POST)
    @ResponseBody
    public PsCollection selectSingleCollectionInfoByCollectionId(Integer collectionId) {
        Integer psUserId=psUserService.getCurrentPsUserId();
        PsCollection psCollection=psCollectionService.selectSingleCollectionInfoByCollectionId(collectionId);
        psCollection.setPsUser(psUserService.selectPsUserGeneralInformationById(psUserId));
        psCollection.setPsLike( psLikeService.selectStatus(collectionId,psUserId));
        return psCollection;
    }

}
