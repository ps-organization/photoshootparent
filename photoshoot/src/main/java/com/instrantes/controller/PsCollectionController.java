package com.instrantes.controller;

import com.alibaba.fastjson.JSONObject;
import com.instrantes.pojo.PsCollection;
import com.instrantes.service.PsCollectionService;
import com.instrantes.service.PsUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/collection")
public class PsCollectionController {
    static Logger logger = LogManager.getLogger(PsCollectionController.class);

    @Autowired
    PsCollectionService psCollectionService;
    @Autowired
    PsUserService psUserService;

    //    此处为获取当前用户id的方法
    protected int getCurrentPsUserId() {
        /**
         *此处需要加入验证，验证是否找到用户ID
         *@param []
         *@return int
         *@date 2017/10/25
         */
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int flag = psUserService.selectPsUserUserIdByName(authentication.getName());
        return flag;
    }

    //根据用户id查询作品的详情
    @RequestMapping(value = "/userCollections/userCollections", method = RequestMethod.POST)
    @ResponseBody
    public List<PsCollection> selectPsCollectionByUserId(Integer id) {
        List<PsCollection> psCollectionList = psCollectionService.selectPsCollectionByUserId(id);
        return psCollectionList;
    }

    @RequestMapping(value = "/publishPic", method = RequestMethod.POST)
    @ResponseBody
    /**
     *批量上传多个图片
     *@param [picLocation]
     *@return void
     *@date 2017/10/20
     */
    public void batchInsertPsCollection(@RequestParam(value = "data") String picLocation[]) {
        //组装成collection对象再放入集合中
        List<PsCollection> psCollectionList = new ArrayList<>();
        int currentUserId = getCurrentPsUserId();
        for (int i = 0, picLocationLen = picLocation.length; i < picLocationLen; i++) {
            //此处为去掉域名存入数据库，但仅限后缀为3个字母的图片
            String str = new String(picLocation[i].substring(picLocation[i].length() - 31));
            System.out.println("location:" + picLocation[i]);
            System.out.println("id:" + currentUserId);
            psCollectionList.add(new PsCollection(currentUserId, str));
//            System.out.println("location:" + psCollection.getCollectionPhotolocation());
        }
        psCollectionService.batchInsertPsCollection(psCollectionList);
    }

    //查询所有作品信息
    @RequestMapping(value = "/allCollection", method = RequestMethod.POST)
    @ResponseBody
    public List<PsCollection> selectAllCollection() {
/**
 *此处应该加入随机显示功能，或者显示最新的，而且需要（大数据库分段查询功能），即每次仅查询一部分图片。而不是整个数据库的所有图片
 *@param []
 *@return java.util.List<com.instrantes.pojo.PsCollection>
 *@date 2017/10/25
 */
        try {
            return psCollectionService.selectAllCollection(getCurrentPsUserId());
        } catch (Exception e) {
            return psCollectionService.selectAllCollection(0);
        }
    }

    //查询个人所有作品信息。此处用了json，因为前端传入的是Json字符串
    @RequestMapping(value = "/personCollection", method = RequestMethod.POST)
    @ResponseBody
    public List<PsCollection> selectCollectionInfoByUserId(@RequestBody String userId) { // 该方法不能用Integer接收
        /**
         *根据用户的ID，获取个人用户的所有作品
         *@param [userId]
         *@return java.util.List<com.instrantes.pojo.PsCollection>
         *@date 2017/10/21
         */
        JSONObject array = JSONObject.parseObject(userId);
        System.out.println("----------------id:" + array.getInteger("userId"));
        return psCollectionService.selectCollectionInfoByUserId(array.getInteger("userId"));
    }

    //单个作品信息
    @RequestMapping(value = "/singleColletion", method = RequestMethod.POST)
    @ResponseBody
    public PsCollection selectSingleCollectionInfoByCollectionId(Integer collectionId){
        logger.info("请求处理");
        logger.trace("trace message");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
        logger.fatal("fatal message");
        return psCollectionService.selectSingleCollectionInfoByCollectionId(collectionId);
    }

}
