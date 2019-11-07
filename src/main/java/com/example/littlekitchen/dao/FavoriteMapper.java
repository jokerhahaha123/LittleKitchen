package com.example.littlekitchen.dao;

import com.example.littlekitchen.entities.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
@Mapper
public interface FavoriteMapper {
    @NotNull
    List<Menu> getFavoriteMenus(Integer userId);
    @NotNull
    int getFavoriteCount(Integer userId);
    @NotNull
    int deleteFavorite(Integer userId,Integer menuId);
    @NotNull
    int addFavorite(Integer userId,Integer menuId);
    @NotNull
    int isFavorite(Integer userId, Integer menuId);
}
