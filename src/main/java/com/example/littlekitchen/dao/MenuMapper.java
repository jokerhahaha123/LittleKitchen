package com.example.littlekitchen.dao;

import com.example.littlekitchen.entities.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {

    List<Integer> getRecommendMenuID();

    List<Menu> getNewMenu();

    List<Menu> getSearchMenu(String searchStr);

    Menu getMenuById(Integer mid);

    Menu getMenuByUserid(Integer uid);

    void addMenu(Menu menu);

    void deleteMenu(Integer mid);

    void updateMenu(Menu menu);
}
