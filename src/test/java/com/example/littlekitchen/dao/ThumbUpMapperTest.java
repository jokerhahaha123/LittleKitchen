package com.example.littlekitchen.dao;

import com.example.littlekitchen.dao.ThumbUpMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThumbUpMapperTest {

    @Autowired
    ThumbUpMapper thumbUpMapper;
    /*测试点赞*/
    @Test
    public void getThumbUpNumber() {
        System.out.println(thumbUpMapper.getThumbUpNumber(1));
    }
    @Test
    public void addThumbUp(){
        if(thumbUpMapper.isThumbUp(2,3)==1) {
            thumbUpMapper.deleteThumbUp(2,3);
        }
        thumbUpMapper.addThumbUp(2, 3);
        Assert.assertTrue(thumbUpMapper.isThumbUp(2, 1) == 1);


    }
    @Test
    public void deleteThumbUp(){
        if(thumbUpMapper.isThumbUp(2,3)==0) {
            thumbUpMapper.addThumbUp(2, 3);
        }
        thumbUpMapper.deleteThumbUp(2, 3);
        Assert.assertTrue(thumbUpMapper.isThumbUp(2,3)==0);
    }


}