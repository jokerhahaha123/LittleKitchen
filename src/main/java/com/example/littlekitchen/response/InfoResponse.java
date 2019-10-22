package com.example.littlekitchen.response;

import java.util.Date;

public class InfoResponse {
    private String nickname;
    private String email;
    private boolean gender;
    private Date birthday;
    private String photo;
    private String description;
    private boolean isSelf;

    public InfoResponse(String nickname, String email, boolean gender, Date birthday, String photo, String description, boolean isSelf) {
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.photo = photo;
        this.description = description;
        this.isSelf = isSelf;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean self) {
        isSelf = self;
    }
}
