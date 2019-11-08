package com.example.littlekitchen.entities;

import java.util.Date;

public class Menu {
    private int menuid;
    private int userid;
    private String title;
    private String description;
    private String cover;
    private String picture;
    private Date createTime;
    private int type;

    public Menu(){

    }

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Menu(int menuid, int userid, String title, String description, String cover, String pitcure, Date createTime, int type) {
        this.menuid = menuid;
        this.userid = userid;
        this.title = title;
        this.description = description;
        this.cover = cover;
        this.picture = pitcure;
        this.createTime = createTime;
        this.type = type;
    }
}
