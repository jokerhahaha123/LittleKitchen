package com.example.littlekitchen.controller;

import com.example.littlekitchen.dao.FavoriteMapper;
import com.example.littlekitchen.entities.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class FavoriteController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    FavoriteMapper favoriteMapper;

    @GetMapping("/littlekitchen/user/{id}/favorites")
    public Map<String,Object> getFavoriteMenus(HttpSession session,@PathVariable("id") Integer id){
        int userId = (Integer)(session.getAttribute("userid"));
        if (id!=null)
            userId = id;
        Integer favoriteCount = favoriteMapper.getFavoriteCount(userId);
        List<Menu> favoriteMenus = favoriteMapper.getFavoriteMenus(userId);
        Map<String,Object> map = new HashMap<>();
        map.put("favoriteCount",favoriteCount);
        map.put("favoriteMenus",favoriteMenus);
        logger.info("查询收藏菜单");
        return map;
    }

    @GetMapping("/littlekichen/updates/addfavorite/{menuid}")
    public void addFavorite(HttpSession session,@NotNull @PathVariable("menuid") Integer menuId){
        logger.info("添加收藏菜单");
        int userId = (Integer)(session.getAttribute("userid"));
        favoriteMapper.addFavorite(userId,menuId);
    }

    @GetMapping("/littlekichen/updates/deletefavorite/{menuid}")
    public void deleteFavorite(HttpSession session,@NotNull @PathVariable("menuid") Integer menuId){
        logger.info("删除收藏菜单");
        int userId = (Integer)(session.getAttribute("userid"));
        favoriteMapper.deleteFavorite(userId,menuId);
    }
}
