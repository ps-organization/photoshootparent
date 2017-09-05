package com.instrantes.service;
import com.instrantes.pojo.PsWatch;

import java.util.List;
public interface PsWatchService{
     PsWatch getPsWatchFansCountByPsUserId(int id) ;
     PsWatch getPsWatchUserCountByPsUserId(int id);
}