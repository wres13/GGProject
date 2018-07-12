package com.dongdian.jj.gorgeous.dto;

/**
 * Created by Jiajun he on 2018/4/16.
 * Email:1021661582@qq.com
 * des: 请求套图实体
 * version: 1.0.0
 */
public class PostListDto {


    /**
     * id : 31
     * userId : 1
     * viewNum : 0
     * likeNum : 0
     * postTime : 1524568274000
     * account : admin
     * postType : 0
     * cover : /preUpdateCover/post_1524568274478.jpg
     * like : false
     * hot : false
     */

    private int id;
    private int userId;
    private int viewNum;
    private int likeNum;
    private long postTime;
    private String account;
    private int postType;
    private String cover;
    private boolean like;
    private boolean hot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public long getPostTime() {
        return postTime;
    }

    public void setPostTime(long postTime) {
        this.postTime = postTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    @Override
    public String toString() {
        return "PostListDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", viewNum=" + viewNum +
                ", likeNum=" + likeNum +
                ", postTime=" + postTime +
                ", account='" + account + '\'' +
                ", postType=" + postType +
                ", cover='" + cover + '\'' +
                ", like=" + like +
                ", hot=" + hot +
                '}';
    }
}
