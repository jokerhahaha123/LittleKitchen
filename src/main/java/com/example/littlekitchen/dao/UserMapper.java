package com.example.littlekitchen.dao;

import com.example.littlekitchen.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Mapper
public interface UserMapper {
    User login(String email,String password);

    User findUserRegister(String email);

    void addUser(String nickname, String email, String password);

    User getInfoById(int userid);

    void updateInfo(int userid,String nickname,boolean gender,Date birthday,String photo,String description);

}
