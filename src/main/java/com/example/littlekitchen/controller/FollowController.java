package com.example.littlekitchen.controller;

import com.example.littlekitchen.dao.FollowMapper;
import com.example.littlekitchen.entities.FollowUser;
import com.example.littlekitchen.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
public class FollowController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    FollowMapper followMapper;
    @GetMapping("/littlekitchen/updates/follows")
    public Map<String,Object> getFollowUsers(HttpSession session){
        session.setAttribute("userid",1);
        int userId = (Integer)(session.getAttribute("userid"));
        int followNumber = followMapper.getFollowNumber(userId);
        List<FollowUser> followUsers = followMapper.getFollowUsers(userId);
        Map<String,Object> map = new HashMap<>();
        map.put("followNumber",followNumber);
        map.put("followUsers",followUsers);
        return map;
    }

    @GetMapping("/littlekitchen/user/{id}/addfollow")
    public Map<String,Object> addFollow(HttpSession session,@NotNull @PathVariable("id") Integer userId2){
        session.setAttribute("userid",1);
        int userId = (Integer)(session.getAttribute("userid"));
        Map<String,Object> map = new HashMap<>();
        Integer mes = 0;
        if(followMapper.isFollowed(userId,userId2)==0) {
            followMapper.addFollow(userId, userId2);
            mes = 1;
        }
        Integer num = followMapper.getFollowNumber(userId);
        map.put("message",mes);
        map.put("followNumber",num);
        return map;
    }

    @GetMapping("/littlekitchen/user/{id}/deletefollow")
    public Map<String,Object> deleteFollow(HttpSession session,@NotNull @PathVariable("id") Integer userId2){
        session.setAttribute("userid",1);
        int userId = (Integer)(session.getAttribute("userid"));
        Integer mes = 0;
        if(followMapper.isFollowed(userId,userId2)==1) {
            followMapper.deleteFollow(userId, userId2);
            mes = 1;
        }
        Integer num = followMapper.getFollowNumber(userId);
        Map<String,Object> map = new HashMap<>();
        map.put("message",mes);
        map.put("followNumber",num);
        return map;
    }
}
