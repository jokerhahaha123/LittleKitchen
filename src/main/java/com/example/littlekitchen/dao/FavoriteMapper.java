package com.example.littlekitchen.dao;

import com.example.littlekitchen.entities.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
@Mapper
public interface FavoriteMapper {
    @NotNull
    List<Menu> getFavoriteMenus(@Param("userId") Integer userId);
    @NotNull
    int getFavoriteCount(@Param("userId") Integer userId);
    @NotNull
    void deleteFavorite(@Param("userId") Integer userId,@Param("menuId") Integer menuId);
    @NotNull
    void addFavorite(@Param("userId")Integer userId,@Param("menuId") Integer menuId);
    @NotNull
    int isFavorite(@Param("userId")Integer userId, @Param("menuId") Integer menuId);
    @NotNull
    int getMenuFavoriteNum(@Param("menuId") Integer menuId);
}
