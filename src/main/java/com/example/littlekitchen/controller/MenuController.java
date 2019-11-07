package com.example.littlekitchen.controller;

import com.example.littlekitchen.dao.MenuMapper;
import com.example.littlekitchen.entities.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MenuMapper menuMapper;

    @GetMapping("/littlekitchen/home/recommend")
    public List<Menu> getRecommend(){
        logger.info("查询推荐菜单");
        List<Menu> re = new ArrayList<>();
        List<Integer> list = menuMapper.getRecommendMenuID();
        for(int i = 0; i < list.size(); i++){
            Menu menu = menuMapper.getMenuById(list.get(i));
            re.add(menu);
        }
        return re;
    }

    @GetMapping("/littlekitchen/home/new")
    public List<Menu> getNew(){
        logger.info("查询最新创建的菜单");
        List<Menu> re = menuMapper.getNewMenu();
        return re;
    }

    @GetMapping("/littlekitchen/type/{typeid}")
    public List<Menu> getType(@PathVariable("typeid") Integer tid){
        logger.info("查询菜系的菜单");
        List<Menu> re = menuMapper.getMenuByType(tid);
        return re;
    }

    @GetMapping("/littlekitchen/home/search/{keyword}")
    public List<Menu> getSearch(@PathVariable("keyword") String searchStr){
        logger.info("搜索菜谱");
        return menuMapper.getSearchMenu(searchStr);
    }

    @GetMapping("/littlekitchen/details/{menuid}")
    public Menu getDetail(@PathVariable("menuid") Integer mid){
        return menuMapper.getMenuById(mid);
    }

    @PutMapping("")
    public void insertMenu(Menu menu){
        menuMapper.addMenu(menu);
    }

    @PostMapping("")
    public void updateMenu(Menu menu){
        menuMapper.updateMenu(menu);
    }

    @DeleteMapping("")
    public void deleteMenu(Integer mid){
        menuMapper.deleteMenu(mid);
    }
}
