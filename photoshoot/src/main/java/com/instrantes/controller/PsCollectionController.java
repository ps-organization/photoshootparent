package com.instrantes.controller;

import com.instrantes.pojo.PsCollection;
import com.instrantes.pojo.PsUser;
import com.instrantes.service.PsCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/collection")
public class PsCollectionController {
    @Autowired
    PsCollectionService psCollectionService;

    //根据用户id查询作品的详情
    @RequestMapping(value = "/userCollections", method = RequestMethod.POST)
    @ResponseBody
    public List<PsCollection> selectPsCollectionByUserId (Integer id){
        List<PsCollection> psCollectionList=psCollectionService.selectPsCollectionByUserId(1);
        System.out.println(psCollectionList.get(1).getCollectionPhotolocation());
        return psCollectionList;
    }
}
