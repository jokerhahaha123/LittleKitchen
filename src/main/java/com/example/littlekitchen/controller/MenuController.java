package com.example.littlekitchen.controller;

import com.example.littlekitchen.component.Tools;
import com.example.littlekitchen.dao.*;
import com.example.littlekitchen.entities.FollowUser;
import com.example.littlekitchen.entities.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/littlekitchen")
public class MenuController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MenuMapper menuMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    FollowMapper followMapper;
    @Autowired
    ThumbUpMapper thumbUpMapper;
    @Autowired
    FavoriteMapper favoriteMapper;

    @GetMapping("/home/recommend")
    public Map<String, Object> getRecommend(){
        logger.info("查询推荐菜单");
        List<Menu> menuList = new ArrayList<>();
        List<Integer> list = menuMapper.getRecommendMenuID();
        for(int i = 0; i < list.size(); i++){
            Menu menu = menuMapper.getMenuById(list.get(i));
            menuList.add(menu);
        }
        return Tools.getMapByMenu(menuList);
    }

    @GetMapping("/home/new")
    public Map<String, Object> getNew(){
        logger.info("查询最新创建的菜单");
        List<Menu> menuList = menuMapper.getNewMenu();
        return Tools.getMapByMenu(menuList);
    }

    @GetMapping("/home/type/{typeid}")
    public Map<String, Object> getType(@PathVariable("typeid") Integer tid){
        logger.info("查询菜系的菜单");
        List<Menu> menuList = menuMapper.getMenuByType(tid);
        return Tools.getMapByMenu(menuList);
    }

    @GetMapping("/home/search/{keyword}")
    public Map<String, Object> getSearch(@PathVariable("keyword") String searchStr){
        logger.info("搜索菜谱");
        List<Menu> menuList = menuMapper.getSearchMenu(searchStr);
        return Tools.getMapByMenu(menuList);
    }

    @GetMapping("/details/{menuid}")
    public Map<String, Object> getDetail(@PathVariable("menuid") Integer mid){
        logger.info("查看菜谱详情");
        int uid = 2;//Integer.parseInt(session.getAttribute("userid").toString());
        Map<String, Object> map = new HashMap<>();
        Menu menu = menuMapper.getMenuById(mid);
        int favNum = favoriteMapper.getMenuFavoriteNum(mid);
        int thuNum = thumbUpMapper.getThumbUpNumber(mid);
        boolean favBol = (favoriteMapper.isFavorite(uid, mid) == 1);
        boolean thuBol = (thumbUpMapper.isThumbUp(uid, mid) == 1);
        map.put("menuDetails", menu);
        map.put("favNum", favNum);
        map.put("thuNum", thuNum);
        map.put("isFav", favBol);
        map.put("isThu", thuBol);
        return map;
    }

    @GetMapping("/updates/list")
    public Map<String, Object> getFollowUpdateList(HttpSession session){
        logger.info("查看关注的人的动态");
        int uid = 2;//Integer.parseInt(session.getAttribute("userid").toString());
        Map<String, Object> map = new HashMap<>();
        List<FollowUser> userList = followMapper.getFollowUsers(uid);
        List<Menu> menuList = new ArrayList<>();
        List<Integer> favList = new ArrayList<>();
        List<Integer> thuList = new ArrayList<>();
        List<Boolean> favBol = new ArrayList<>();
        List<Boolean> thuBol = new ArrayList<>();
        for(int i = 0; i < userList.size(); i++){
            int userId = userList.get(i).getUserid();
            menuList.addAll(menuMapper.getMenuByUserid(userId));
        }
        Tools.sortMenuByDate(menuList);
        for(int i = 0; i < menuList.size(); i++){
            int mid = menuList.get(i).getMenuid();
            int favNum = favoriteMapper.getMenuFavoriteNum(mid);
            favList.add(favNum);
            int thuNum = thumbUpMapper.getThumbUpNumber(mid);
            thuList.add(thuNum);
            int isFavNum = favoriteMapper.isFavorite(uid, mid);
            boolean isFav = (isFavNum == 1);
            favBol.add(isFav);
            int isThuNum = thumbUpMapper.isThumbUp(uid, mid);
            boolean isThu = (isThuNum == 1);
            thuBol.add(isThu);
        }
        map.put("menu", menuList);
        map.put("favoriteNum", favList);
        map.put("ThumbUpNum", thuList);
        map.put("isFavorite", favBol);
        map.put("isThumbUp", thuBol);
        return map;
    }

    @GetMapping("/my/{userid}")
    public List<Menu> getMyMenu(@PathVariable("userid") Integer uid){
        return menuMapper.getMenuByUserid(uid);
    }

    @PutMapping("/createNew")
    public void insertMenu(Menu menu){
        logger.info("创建新菜谱");
        menuMapper.addMenu(menu);
    }

    @PostMapping("/update/{menuid}")
    public void updateMenu(Menu menu){
        logger.info("更新已创建的菜谱");
        menuMapper.updateMenu(menu);
    }

    @DeleteMapping("/delete/{menuid}")
    public void deleteMenu(Integer mid){
        logger.info("删除已创建的菜谱");
        menuMapper.deleteMenu(mid);
    }
}
