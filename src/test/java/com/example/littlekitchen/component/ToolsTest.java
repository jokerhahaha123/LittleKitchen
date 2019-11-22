package com.example.littlekitchen.component;

import com.example.littlekitchen.entities.Menu;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ToolsTest {

    @Test
    public void getMapByMenu() {
    }

    @Test
    public void sortMenuByDate() throws ParseException {
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu(3,1,"title1", "des1", "cover1", null, null, null, dateFormat2.parse("2019-09-11 11:00:00"), 1));
        menuList.add(new Menu(2,1,"title1", "des1", "cover1", null, null, null, dateFormat2.parse("2019-09-10 11:00:00"), 1));
        menuList.add(new Menu(1,1,"title1", "des1", "cover1", null, null, null, dateFormat2.parse("2019-09-12 11:00:00"), 1));
        menuList.add(new Menu(4,1,"title1", "des1", "cover1", null, null, null, dateFormat2.parse("2019-09-12 10:12:00"), 1));
        Tools.sortMenuByDate(menuList);
        for(int i = 0; i < menuList.size(); i++){
            System.out.println(menuList.get(i).getCreateTime());
        }

    }
}