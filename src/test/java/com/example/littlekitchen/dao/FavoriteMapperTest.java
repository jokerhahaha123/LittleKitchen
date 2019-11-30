package com.example.littlekitchen.dao;

import com.example.littlekitchen.dao.ThumbUpMapper;
import com.example.littlekitchen.entities.Menu;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FavoriteMapperTest {

    @Autowired
    FavoriteMapper favoriteMapper;
    @Test
    public void getFavoriteMenus() {
       List<Menu> menus = favoriteMapper.getFavoriteMenus(1);
        Assert.assertTrue(menus.size()!=0);
    }

    @Test
    public void getFavoriteCount() {
        Assert.assertTrue(favoriteMapper.getFavoriteCount(1)>0);
    }

    @Test
    public void deleteFavorite() {
        if(favoriteMapper.isFavorite(1,3)==0) {
            favoriteMapper.addFavorite(1, 3);
        }
        Assert.assertTrue(favoriteMapper.isFavorite(1,3)==1);
        favoriteMapper.deleteFavorite(1,3);
        Assert.assertTrue(favoriteMapper.isFavorite(1,3)==0);
    }


    @Test
    public void addFavorite() {
        if(favoriteMapper.isFavorite(1,3)==1){
            favoriteMapper.deleteFavorite(1,3);
        }
        favoriteMapper.addFavorite(1,3);
        Assert.assertTrue(favoriteMapper.isFavorite(1,3)==1);
    }

    @Test
    public void isFavorite() {
        if(favoriteMapper.isFavorite(1,3)==1){
            favoriteMapper.deleteFavorite(1,3);
        }
        favoriteMapper.addFavorite(1,3);
        Assert.assertTrue(favoriteMapper.isFavorite(1,3)==1);
    }

    @Test
    public void getMenuFavoriteNum() {
        Assert.assertTrue(favoriteMapper.getMenuFavoriteNum(1)>=0);
    }

}