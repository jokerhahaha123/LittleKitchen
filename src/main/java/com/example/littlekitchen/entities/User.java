package com.example.littlekitchen.entities;

import java.util.Date;

public class User {
    private int userid;
    private String email;
    private String password;
    private String nickname;
    private boolean gender;
    private Date birthday;
    private Date createDate;
    private String photo;
    private String description;

    public User() {}

    public User(int userid, String email, String password, String nickname, boolean gender, Date birthday, Date createDate, String photo, String description) {
        this.userid = userid;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.createDate = createDate;
        this.photo = photo;
        this.description = description;
    }
/*
    public User(int userid, String email, String password, String nickname, boolean gender, java.sql.Date birthday, String photo, String description, java.sql.Timestamp createDate) {
        this.userid = userid;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = new java.util.Date(birthday.getTime());
        this.photo = photo;
        this.description = description;
        this.createDate = createDate;
    }*/

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getPassword() {
        return password;
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

    public Date getCreateDate() {
        return createDate;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDescription() {
        return description;
    }
}
