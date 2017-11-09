package com.instrantes.pojo;

import java.io.Serializable;

public class PsLike implements Serializable {
    private Integer likeId;
    private Integer likeCollectionid;
    private Integer likeUserid;
    private java.util.Date likeCreatetime;
    private Integer likeStatus;
    public PsLike() {
        super();
    }
    public PsLike(Integer likeId,Integer likeCollectionid,Integer likeUserid,java.util.Date likeCreatetime,Integer likeStatus) {
        super();
        this.likeId = likeId;
        this.likeCollectionid = likeCollectionid;
        this.likeUserid = likeUserid;
        this.likeCreatetime = likeCreatetime;
        this.likeStatus = likeStatus;
    }
    public Integer getLikeId() {
        return this.likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getLikeCollectionid() {
        return this.likeCollectionid;
    }

    public void setLikeCollectionid(Integer likeCollectionid) {
        this.likeCollectionid = likeCollectionid;
    }

    public Integer getLikeUserid() {
        return this.likeUserid;
    }

    public void setLikeUserid(Integer likeUserid) {
        this.likeUserid = likeUserid;
    }

    public java.util.Date getLikeCreatetime() {
        return this.likeCreatetime;
    }

    public void setLikeCreatetime(java.util.Date likeCreatetime) {
        this.likeCreatetime = likeCreatetime;
    }

    public Integer getLikeStatus() {
        return this.likeStatus;
    }

    public void setLikeStatus(Integer likeStatus) {
        this.likeStatus = likeStatus;
    }

}
