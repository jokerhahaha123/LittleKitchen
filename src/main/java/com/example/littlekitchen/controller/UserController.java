package com.example.littlekitchen.controller;

import com.example.littlekitchen.Request.LoginRequest;
import com.example.littlekitchen.Request.RegisterRequest;
import com.example.littlekitchen.Request.UpdateInfoRequest;
import com.example.littlekitchen.dao.UserMapper;
import com.example.littlekitchen.entities.FollowUser;
import com.example.littlekitchen.entities.User;
import com.example.littlekitchen.response.InfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.Date;
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
        Map<String,String> message = new HashMap<>();
        System.out.println(loginRequest.getEmail() + loginRequest.getPassword());
        User user = userMapper.login(loginRequest.getEmail(),loginRequest.getPassword());
        if (user != null) {
            //登录成功
            session.setAttribute("userid", user.getUserid());
            logger.info("用户" + user.getUserid() + "登陆成功");
            message.put("email",user.getEmail());
            message.put("photo",user.getPhoto());
            message.put("nickname",user.getNickname());
            return message;
//            return user;
            //重定向 redirect：可以重定向到任意一个请求中（包括其他项目），地址栏改变
//                return "redirect:/main.html";
        }
        return null;
    }

    @PutMapping("/register")
    public @ResponseBody
    Map register(@RequestBody RegisterRequest registerRequest) {
        Map<String, String> message = new HashMap<>();
        User user = userMapper.findUserRegister(registerRequest.getEmail());
        if (user == null) {
            userMapper.addUser(registerRequest.getNickname(),registerRequest.getEmail(),registerRequest.getPassword());
            message.put("message","1");
            logger.info(registerRequest.getEmail()+"注册成功");
            return message;
        }
        message.put("message","0");
        logger.info(registerRequest.getEmail()+"注册失败");
        return message;
    }

    @GetMapping("/user/{id}/info")
    public @ResponseBody
    Object getInfo(HttpSession session, @PathVariable("id") int id) {
        Map<String, Object> message = new HashMap<>();
        User user = userMapper.getInfoById(id);
        FollowUser userInfo = new FollowUser(user.getUserid(),user.getEmail(),user.getNickname(),user.isGender(),user.getBirthday(),user.getPhoto(),user.getDescription());
        if (user != null) {
//            session.setAttribute("userid",1);
            if (Integer.parseInt(session.getAttribute("userid").toString())==id) {
                logger.info("用户"+session.getAttribute("userid").toString()+"查看自己的个人信息");
                message.put("user",userInfo);
                message.put("isSelf",true);
                return message;
            }
            logger.info("用户"+session.getAttribute("userid").toString()+"查看用户"+id+"的个人信息");
            message.put("isSelf",false);
            return message;
        }
        return null;
    }

    @PostMapping("/user/{id}/updateInfo")
    public @ResponseBody
    Map updateInfo(HttpSession session, @PathVariable("id") int id, @RequestBody UpdateInfoRequest updateInfoRequest) {
        Map<String, String> message = new HashMap<>();
        try {
            userMapper.updateInfo(id, updateInfoRequest.getNickname(),updateInfoRequest.isGender(),updateInfoRequest.getBirthday(),updateInfoRequest.getPhoto(),updateInfoRequest.getDescription());
        }catch (Exception e){
            System.out.println(e.getMessage());
            message.put("message","0");
            logger.info("用户"+id+"更新个人信息失败！");
            return message;
        }
        message.put("message","1");
        logger.info("用户"+id+"更新个人信息成功！");
        return message;
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
