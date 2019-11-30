package com.example.littlekitchen.dao;

import com.example.littlekitchen.entities.FollowUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
@Repository
@Mapper
public interface FollowMapper {
    @NotNull
    int getFollowNumber(@Param("userId") Integer userId);
    @NotNull
    List<FollowUser>  getFollowUsers(@Param("userId") Integer userId);
    @NotNull
    void addFollow(@Param("userId1") Integer userId1,@Param("userId2") Integer userId2);
    @NotNull
    void deleteFollow(@Param("userId1") Integer userId1,@Param("userId2") Integer userId2);
    @NotNull
    int isFollowed(@Param("userId1") Integer userId1,@Param("userId2") Integer userId2);
    @NotNull
    int getFollowedNumber(@Param("userId") Integer userId);
}
