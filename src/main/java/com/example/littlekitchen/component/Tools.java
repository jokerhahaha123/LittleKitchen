package com.example.littlekitchen.component;

import com.example.littlekitchen.dao.MenuMapper;
import com.example.littlekitchen.dao.UserMapper;
import com.example.littlekitchen.entities.Menu;
import com.example.littlekitchen.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Tools {
    private static MenuMapper menuMapper;
    private static UserMapper userMapper;

    @Autowired
    public Tools(MenuMapper menuMapper, UserMapper userMapper) {
        Tools.menuMapper = menuMapper;
        Tools.userMapper = userMapper;
    }


    public static Map<String, Object> getMapByMenu(List<Menu> menuList){
        Map<String, Object> map = new HashMap<>();
        List<User> userList = new ArrayList<>();
        for(int i = 0; i < menuList.size(); i++){
            int menuid = menuList.get(i).getMenuid();
            int userid = menuMapper.getUseridByMenuid(menuid);
            userList.add(userMapper.getInfoById(userid));
        }
        map.put("menu", menuList);
        map.put("user", userList);
        return map;
    }
}
