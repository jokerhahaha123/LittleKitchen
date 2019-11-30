package com.example.littlekitchen.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Repository
@Mapper
public interface ThumbUpMapper {
    @NotNull
    int getThumbUpNumber(@Param("menuId") Integer menuId);
    @NotNull
    void addThumbUp(@Param("userId") Integer userId, @Param("menuId") Integer menuId);
    @NotNull
    void deleteThumbUp(@Param("userId") Integer userId,@Param("menuId") Integer menuId);
    @NotNull
    int isThumbUp(@Param("userId") Integer userId,@Param("menuId") Integer menuId);
}
