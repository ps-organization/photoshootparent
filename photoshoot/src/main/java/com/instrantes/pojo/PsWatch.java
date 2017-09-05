package com.instrantes.pojo;
public class PsWatch {
    private Integer watchUserid;
    private Integer watchFansid;
    private  PsUser psUser;

    public PsUser getPsUser() {
        return psUser;
    }

    public void setPsUser(PsUser psUser) {
        this.psUser = psUser;
    }

    public PsWatch() {
        super();
    }
    public PsWatch(Integer watchUserid,Integer watchFansid) {
        super();
        this.watchUserid = watchUserid;
        this.watchFansid = watchFansid;
    }
    public Integer getWatchUserid() {
        return this.watchUserid;
    }

    public void setWatchUserid(Integer watchUserid) {
        this.watchUserid = watchUserid;
    }

    public Integer getWatchFansid() {
        return this.watchFansid;
    }

    public void setWatchFansid(Integer watchFansid) {
        this.watchFansid = watchFansid;
    }

}
