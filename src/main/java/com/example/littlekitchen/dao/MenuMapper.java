package com.example.littlekitchen.dao;

import com.example.littlekitchen.entities.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MenuMapper {

    List<Integer> getRecommendMenuID();

    List<Menu> getNewMenu();

    List<Menu> getSearchMenu(String searchStr);

    Menu getMenuById(Integer mid);

    List<Menu> getMenuByUserid(Integer uid);

    List<Menu> getMenuByType(Integer type);

    Integer getUseridByMenuid(Integer mid);

    void addMenu(Menu menu);

    void deleteMenu(Integer mid);

    void updateMenu(Menu menu);
}
