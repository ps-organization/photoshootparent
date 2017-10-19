package com.instrantes.controller;

import com.instrantes.pojo.PsCollection;
import com.instrantes.service.PsCollectionService;
import com.instrantes.service.PsUserService;
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
    @Autowired
    PsCollectionService psCollectionService;
    @Autowired
    PsUserService psUserService;

    //    此处为获取当前用户id的方法
    protected int getCurrentPsUserId() {
        System.out.println( SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        return psUserService.selectPsUserUserIdByName(authentication.getName());
    }

    //根据用户id查询作品的详情
    @RequestMapping(value = "/userCollections/userCollections", method = RequestMethod.POST)
    @ResponseBody
    public List<PsCollection> selectPsCollectionByUserId(Integer id) {
        List<PsCollection> psCollectionList = psCollectionService.selectPsCollectionByUserId(id);
        return psCollectionList;
    }

    //    批量上传多个图片
    @RequestMapping(value = "/publishPic", method = RequestMethod.POST)
    @ResponseBody
    public void batchInsertPsCollection(@RequestParam(value = "data") String picLocation[]) {
        System.out.println("arrayList:" + picLocation);
        System.out.println("arrayListSize:" + picLocation.length);
        //组装成collection对象再放入集合中
        List<PsCollection> psCollectionList = new ArrayList<>();
        for (int i = 0, picLocationLen = picLocation.length; i < picLocationLen; i++) {
            PsCollection psCollection = new PsCollection();
            psCollection.setCollectionUserid(getCurrentPsUserId());
            //此处为去掉域名存入数据库，但仅限后缀为3个字母的图片
            String str=picLocation[i].substring(picLocation[i].length()-31);
            psCollection.setCollectionPhotolocation(str);
            psCollectionList.add(psCollection);
            System.out.println("s");
        }
         psCollectionService.batchInsertPsCollection(psCollectionList);
    }

    //查询所有作品信息
    @RequestMapping(value = "/allCollection", method = RequestMethod.POST)
    @ResponseBody
    public List<PsCollection> selectAllCollection() {
        /**
        *
        *@param []
        *@return java.util.List<com.instrantes.pojo.PsCollection>
        *@date 2017/10/19
        */

        return  psCollectionService.selectAllCollection();
    }

    //查询个人所有作品信息
    @RequestMapping(value = "/personCollection", method = RequestMethod.POST)
    @ResponseBody
    public List<PsCollection> selectCollectionInfoByUserId(Integer userId) {
        return  psCollectionService.selectCollectionInfoByUserId(userId);
    }

}
