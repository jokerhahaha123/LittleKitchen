package com.example.littlekitchen.entities;

import java.util.Date;
import java.util.List;

public class Menu {
    private int menuid;
    private int userid;
    private String title;
    private String description;
    private String cover;
    private List<String> picture;
    private List<String> step;
    private List<String> material;
    private Date createTime;
    private int type;

    public Menu(){

    }

    public Menu(int menuid, int userid, String title, String description, String cover, List<String> picture, List<String> step, List<String> material, Date createTime, int type) {
        this.menuid = menuid;
        this.userid = userid;
        this.title = title;
        this.description = description;
        this.cover = cover;
        this.picture = picture;
        this.step = step;
        this.material = material;
        this.createTime = createTime;
        this.type = type;
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

    public Date getCreateTime() {
        return createTime;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public List<String> getStep() {
        return step;
    }

    public void setStep(List<String> step) {
        this.step = step;
    }

    public List<String> getMaterial() {
        return material;
    }

    public void setMaterial(List<String> material) {
        this.material = material;
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

}
