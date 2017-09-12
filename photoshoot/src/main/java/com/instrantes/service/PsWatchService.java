package com.instrantes.service;
import com.instrantes.pojo.PsWatch;

import java.util.List;
public interface PsWatchService{
     PsWatch getPsWatchFansCountByPsUserId(int id) ;
     PsWatch getPsWatchUserCountByPsUserId(int id);
     //通过当前用户id,获取所有粉丝头像姓名简略信息
     public List<PsWatch> getPsWatchFans(Integer id);
     //通过当前用户id,获取所有关注头像姓名简略信息
     public List<PsWatch> getPsWatchUser(Integer id);
}