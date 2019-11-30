package com.example.littlekitchen.controller;

import com.example.littlekitchen.dao.ThumbUpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ThumbUpController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ThumbUpMapper thumbUpMapper;

    @GetMapping("/littlekitchen/updates/addthumbup/{menuid}")
    public void addThumbup(HttpSession session, @NotNull @PathVariable("menuid") Integer menuId){
        logger.info("新增点赞");
        session.setAttribute("userid",1);
        int userId = (Integer)(session.getAttribute("userid"));
        thumbUpMapper.addThumbUp(userId,menuId);
    }
    @GetMapping("/littlekitchen/updates/deletethumbup/{menuid}")
    public void deleteThumbup(HttpSession session, @NotNull @PathVariable("menuid") Integer menuId){
        logger.info("取消点赞");
        session.setAttribute("userid",1);
        int userId = (Integer)(session.getAttribute("userid"));
        thumbUpMapper.deleteThumbUp(userId,menuId);
    }
    @GetMapping("/littlekitchen/home/ifthumbup/{menuid}")
    public Map<String,Object> ifThumbup(HttpSession session, @NotNull @PathVariable("menuid") Integer menuId){
        logger.info("是否点赞");
        session.setAttribute("userid",1);
        int userId = (Integer)(session.getAttribute("userid"));
        Boolean ifThum = thumbUpMapper.isThumbUp(userId,menuId)>0;
        Map<String,Object> result = new HashMap<>();
        result.put("ifThumbup",ifThum);
        return result;
    }


}
