package com.instrantes.controller;

import com.instrantes.pojo.PsWatch;
import com.instrantes.service.PsWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/psWatch")
public class PsWatchController {
    @Autowired
    PsWatchService psWatchService;

    //通过当前用户id,获取所有粉丝头像姓名简略信息
    @RequestMapping(value = "/psWatchFans", method = RequestMethod.POST)
    @ResponseBody
    public List<PsWatch> getPsWatchFans(Integer id) {
        return psWatchService.getPsWatchFans(id);
    }

    //通过当前用户id,获取所有关注头像姓名简略信息
    @RequestMapping(value = "/psWatchUser", method = RequestMethod.POST)
    @ResponseBody
    public List<PsWatch> getPsWatchUser(Integer id) {
        return psWatchService.getPsWatchUser(id);
    }

}
