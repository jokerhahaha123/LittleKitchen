package com.example.littlekitchen.dao;

import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Repository
public interface ThumbUpMapper {
    @NotNull
    int getThumbUpNumber(Integer menuId);
    @NotNull
    int addThumbUp(Integer userId,Integer menuId);
    @NotNull
    int deleteThumbUp(Integer userId,Integer menuId);
    @NotNull
    int isThumbUp(Integer userId,Integer menuId);
}
