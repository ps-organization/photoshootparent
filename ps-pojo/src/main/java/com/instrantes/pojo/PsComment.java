package com.instrantes.pojo;

import java.util.Date;

public class PsComment {

    private Integer commentReplyId;//回复别人的id
    private java.util.Date commentTime;
    private Integer commentCollectionid;
    private String commentContent;
    private Integer commentUserid;
    private Integer commentStatus;

    public PsComment(Integer commentReplyId, Date commentTime, Integer commentCollectionid, String commentContent, Integer commentUserid, Integer commentStatus) {
        this.commentReplyId = commentReplyId;
        this.commentTime = commentTime;
        this.commentCollectionid = commentCollectionid;
        this.commentContent = commentContent;
        this.commentUserid = commentUserid;
        this.commentStatus = commentStatus;
    }

    public Integer getCommentReplyId() {
        return commentReplyId;
    }

    public void setCommentReplyId(Integer commentReplyId) {
        this.commentReplyId = commentReplyId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getCommentCollectionid() {
        return commentCollectionid;
    }

    public void setCommentCollectionid(Integer commentCollectionid) {
        this.commentCollectionid = commentCollectionid;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getCommentUserid() {
        return commentUserid;
    }

    public void setCommentUserid(Integer commentUserid) {
        this.commentUserid = commentUserid;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }
}
