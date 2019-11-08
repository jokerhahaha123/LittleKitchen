package com.example.littlekitchen.controller;

import com.example.littlekitchen.dao.FollowMapper;
import com.example.littlekitchen.entities.FollowUser;
import com.example.littlekitchen.entities.User;
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
public class FollowController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    FollowMapper followMapper;
    @GetMapping("/littlekitchen/updates/follows")
    public Map<String,Object> getFollowUsers(HttpSession session){
        int userId = (Integer)(session.getAttribute("userid"));
        int followNumber = followMapper.getFollowNumber(userId);
        List<FollowUser> followUsers = followMapper.getFollowUsers(userId);
        Map<String,Object> map = new HashMap<>();
        map.put("followNumber",followNumber);
        map.put("followUsers",followUsers);
        return map;
    }

    @GetMapping("/littlekitchen/user/{id}/addfollow")
    public void addFollow(HttpSession session,@NotNull @PathVariable("id") Integer userId2){
        int userId = (Integer)(session.getAttribute("userid"));
        followMapper.addFollow(userId,userId2);
    }

    @GetMapping("/littlekitchen/user/{id}/deletefollow")
    public void deleteFollow(HttpSession session,@NotNull @PathVariable("id") Integer userId2){
        int userId = (Integer)(session.getAttribute("userid"));
        followMapper.deleteFollow(userId,userId2);
    }
}
