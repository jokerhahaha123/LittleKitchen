package com.example.littlekitchen.dao;

import com.example.littlekitchen.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Mapper
public interface UserMapper {
    User login(@Param("email")String email,@Param("password")String password);

    User findUserRegister(@Param("email")String email);

    void addUser(@Param("nickname") String nickname, @Param("email")String email, @Param("password")String password);

    User getInfoById(@Param("userid")int userid);

    void updateInfo(@Param("userid")int userid,@Param("nickname") String nickname,@Param("gender") boolean gender,@Param("birthday") Date birthday,@Param("photo") String photo,@Param("description") String description);

}
