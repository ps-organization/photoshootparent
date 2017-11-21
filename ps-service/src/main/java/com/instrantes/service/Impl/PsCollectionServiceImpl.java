package com.instrantes.service.Impl;
import com.instrantes.dao.PsCollectionDao;
import com.instrantes.pojo.PsCollection;
import com.instrantes.service.PsCollectionService;
import com.instrantes.service.PsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PsCollectionServiceImpl implements PsCollectionService {
    @Autowired
    private PsCollectionDao psCollectionDao;


    //    根据用户id查询作品信息
    public List<PsCollection> selectPsCollectionByUserId (Integer id){
        return psCollectionDao.selectPsCollectionByUserId(id);
    }

    //查询所有作品信息
    public List<PsCollection> selectAllCollection(Integer likeUserId,Integer currentPicId,Integer picLoadNum){
        return psCollectionDao.selectAllCollection(likeUserId,currentPicId,picLoadNum);
    }
    //查询个人所有作品信息
    @Cacheable(value = "psCollection",key="'userId'+#userId.toString()")
    public List<PsCollection> selectCollectionInfoByUserId(Integer userId) {
        return psCollectionDao.selectCollectionInfoByUserId(userId);
    }

    /**
     *批量上传多个图片
     * @param hashMap key有两个picArray（大图）smallPicArray（小图）
     * @param currentUserId 发布图片用户的ID
     *@return void
     *Date: 2017/11/21
     */
    @PreAuthorize("hasAuthority('ROLE_ORDIN')")
    @CacheEvict(value="psCollection",allEntries=true)
    public void batchInsertPsCollection(HashMap<String,ArrayList<String>> hashMap, Integer currentUserId) {
        //组装成collection对象再放入集合中
        List<PsCollection> psCollectionList = new ArrayList<>();
        for (String itemStr : hashMap.get("picArray")) {
            // 60为http://localhost:8080/upload/images/xxxx/xx/xxxxxxxxx.jpg的长度
            if (itemStr.length() == 60) {
                //此处为去掉域名存入数据库，但仅限后缀为3个字母的图片,还有缩略图位置
                String str = itemStr.substring(itemStr.length() - 31);
                psCollectionList.add(new PsCollection(currentUserId, str,"small_"+str));
            } else {
                //此处为去掉域名存入数据库，但仅限后缀为4个字母的图片
                String str = itemStr.substring(itemStr.length() - 32);
                psCollectionList.add(new PsCollection(currentUserId, str,"small_"+str));
            }
        }
        psCollectionDao.batchInsertPsCollection(psCollectionList);
    }

    public PsCollectionDao getPsCollectionDao() {
        return this.psCollectionDao;
    }
    public void setPsCollectionDao(PsCollectionDao psCollectionDao) {
        this.psCollectionDao = psCollectionDao;
    }

    public PsCollection selectSingleCollectionInfoByCollectionId(Integer collectionId){
        return psCollectionDao.selectSingleCollectionInfoByCollectionId(collectionId);
    }
}