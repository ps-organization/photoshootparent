package com.instrantes.dao;

import com.instrantes.pojo.PsWatch;
import org.springframework.stereotype.Repository;

@Repository
public interface PsWatchDao{
    PsWatch getPsWatchFansCountByPsUserId(int id);
    PsWatch getPsWatchUserCountByPsUserId(int id);
//    long getPsWatchRowCount();
//    List<PsWatch> selectPsWatch();
//    PsWatch selectPsWatchById(Object id);
//    int insertPsWatch(PsWatch value);
//    int insertNonEmptyPsWatch(PsWatch value);
//    int deletePsWatchById(Object id);
//    int updatePsWatchById(PsWatch enti);
//    int updateNonEmptyPsWatchById(PsWatch enti);
}