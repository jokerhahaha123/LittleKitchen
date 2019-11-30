package com.example.littlekitchen.dao;
import com.example.littlekitchen.entities.FollowUser;
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
public class FollowMapperTest {

    @Autowired
    FollowMapper followMapper;

    @Test
    public void getFollowNumber() {
        Assert.assertTrue(followMapper.getFollowNumber(1)>=0);
    }

    @Test
    public void getFollowUsers() {
        List<FollowUser> list = followMapper.getFollowUsers(1);
        Assert.assertTrue(list.size()>=0);
    }

    @Test
    public void addFollow() {
        if(followMapper.isFollowed(1,2)==1){
            followMapper.deleteFollow(1,2);
        }
        followMapper.addFollow(1,2);
    }

    @Test
    public void deleteFollow() {
        if (followMapper.isFollowed(1,2)==0){
            followMapper.addFollow(1,2);
        }
        followMapper.deleteFollow(1,2);
    }

    @Test
    public void isFollowed() {
        if (followMapper.isFollowed(1,2)==0){
            followMapper.addFollow(1,2);
        }
        Assert.assertTrue(followMapper.isFollowed(1,2)==1);
    }

    @Test
    public void getFollowedNumber() {
        Assert.assertTrue(followMapper.getFollowNumber(1)>=0);
    }
}