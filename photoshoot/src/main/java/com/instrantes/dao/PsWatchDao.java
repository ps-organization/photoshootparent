package com.instrantes.dao;

import com.instrantes.pojo.PsWatch;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PsWatchDao{
    PsWatch getPsWatchFansCountByPsUserId(int id);
    PsWatch getPsWatchUserCountByPsUserId(int id);

    //通过当前用户id,获取所有粉丝头像姓名简略信息
    List<PsWatch> getPsWatchFans(Integer id);
    //通过当前用户id,获取所有关注头像姓名简略信息
    List<PsWatch> getPsWatchUser(Integer id);

//    long getPsWatchRowCount();
//    List<PsWatch> selectPsWatch();
//    PsWatch selectPsWatchById(Object id);
//    int insertPsWatch(PsWatch value);
//    int insertNonEmptyPsWatch(PsWatch value);
//    int deletePsWatchById(Object id);
//    int updatePsWatchById(PsWatch enti);
//    int updateNonEmptyPsWatchById(PsWatch enti);
}