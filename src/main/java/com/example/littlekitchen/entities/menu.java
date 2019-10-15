package com.example.littlekitchen.entities;

import java.util.Date;

public class menu {
    private int menuid;
    private int userid;
    private String title;
    private String description;
    private String cover;
    private String pitcure;
    private Date create_time;

    public menu(){

    }

    public menu(int menuid, int userid, String title, String description, String cover, String pitcure, Date create_time) {
        this.menuid = menuid;
        this.userid = userid;
        this.title = title;
        this.description = description;
        this.cover = cover;
        this.pitcure = pitcure;
        this.create_time = create_time;
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

    public String getPitcure() {
        return pitcure;
    }

    public void setPitcure(String pitcure) {
        this.pitcure = pitcure;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
