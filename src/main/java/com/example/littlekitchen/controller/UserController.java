package com.example.littlekitchen.controller;

import com.example.littlekitchen.Request.LoginRequest;
import com.example.littlekitchen.Request.RegisterRequest;
import com.example.littlekitchen.Request.UpdateInfoRequest;
import com.example.littlekitchen.dao.UserMapper;
import com.example.littlekitchen.entities.FollowUser;
import com.example.littlekitchen.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/littlekitchen")
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    public @ResponseBody
    Object login(HttpSession session, @RequestBody LoginRequest loginRequest) {
        Map<String,String> map = new HashMap<>();
        User user = userMapper.login(loginRequest.getEmail(),loginRequest.getPassword());
        if (user != null) {
            //登录成功
            session.setAttribute("userid", user.getUserid());
            logger.info("用户" + user.getUserid() + "登陆成功");
            map.put("message","1");
            map.put("userid",String.valueOf(user.getUserid()));
            map.put("email",user.getEmail());
            map.put("photo",user.getPhoto());
            map.put("nickname",user.getNickname());
            return map;
//            return user;
            //重定向 redirect：可以重定向到任意一个请求中（包括其他项目），地址栏改变
//                return "redirect:/main.html";
        }
        logger.info(loginRequest.getEmail()+"登陆失败");
        map.put("message","0");
        return map;
    }

    @PutMapping("/register")
    public @ResponseBody
    Map register(@RequestBody RegisterRequest registerRequest) {
        Map<String, String> map = new HashMap<>();
        User user = userMapper.findUserRegister(registerRequest.getEmail());
        if (user == null) {
            userMapper.addUser(registerRequest.getNickname(),registerRequest.getEmail(),registerRequest.getPassword());
            map.put("message","1");
            logger.info(registerRequest.getEmail()+"注册成功");
            return map;
        }
        map.put("message","0");
        logger.info(registerRequest.getEmail()+"注册失败");
        return map;
    }

    @GetMapping("/user/{id}/info")
    public @ResponseBody
    Object getInfo(HttpSession session, @PathVariable("id") int id) {
        Map<String, Object> map = new HashMap<>();
        User user = userMapper.getInfoById(id);
        FollowUser userInfo = new FollowUser(user.getUserid(),user.getEmail(),user.getNickname(),user.isGender(),user.getBirthday(),user.getPhoto(),user.getDescription());
        if (user != null) {
//            session.setAttribute("userid",1);
            map.put("user",userInfo);
            if (Integer.parseInt(session.getAttribute("userid").toString())==id) {
                logger.info("用户"+session.getAttribute("userid").toString()+"查看自己的个人信息");
                map.put("isSelf",true);
                return map;
            }
            logger.info("用户"+session.getAttribute("userid").toString()+"查看用户"+id+"的个人信息");
            map.put("isSelf",false);
            return map;
        }
        return null;
    }

    @PostMapping("/user/{id}/updateInfo")
    public @ResponseBody
    Map updateInfo(HttpSession session, @PathVariable("id") int id, @RequestBody UpdateInfoRequest updateInfoRequest) {
        Map<String, String> map = new HashMap<>();
        try {
            userMapper.updateInfo(id, updateInfoRequest.getNickname(),updateInfoRequest.isGender(),updateInfoRequest.getBirthday(),updateInfoRequest.getPhoto(),updateInfoRequest.getDescription());
        }catch (Exception e){
            System.out.println(e.getMessage());
            map.put("message","0");
            logger.info("用户"+id+"更新个人信息失败！");
            return map;
        }
        map.put("message","1");
        logger.info("用户"+id+"更新个人信息成功！");
        return map;
    }

    /**
     * 退出登录
     *
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //1. 清空session中的用户信息
        session.removeAttribute("loginUser");
        //2. 再将session进行注销
        session.invalidate();
        //3. 返回登录页面
        return "redirect:/index.html";
    }

}
