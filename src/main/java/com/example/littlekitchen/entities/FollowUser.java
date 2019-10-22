package com.example.littlekitchen.entities;

import java.util.Date;

public class FollowUser {
    private int userid;
    private String email;
    private String nickname;
    private boolean gender;
    private Date birthday;
    private String photo;
    private String description;

    public FollowUser(){

    }

    public FollowUser(int userid, String email, String nickname, boolean gender, Date birthday, String photo, String description) {
        this.userid = userid;
        this.email = email;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.photo = photo;
        this.description = description;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserid() {
        return userid;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }



    public String getphoto() {
        return photo;
    }

    public String getDescription() {
        return description;
    }
}
