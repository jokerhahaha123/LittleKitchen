package com.example.littlekitchen.controller;

import com.example.littlekitchen.dao.UserMapper;
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
    Object login(HttpSession session, @NotNull String email, @NotNull String password) {

        User user = userMapper.login(email, password);
        if (user != null) {
            //登录成功
            session.setAttribute("userid", user.getUserid());
            logger.info("用户" + user.getUserid() + "登陆成功");
            return new User(user.getUserid(), user.getEmail(), user.getNickname(), user.isGender(), user.getBirthday(), user.getCreate_date(), user.getPhoto(), user.getDescription());
            //重定向 redirect：可以重定向到任意一个请求中（包括其他项目），地址栏改变
//                return "redirect:/main.html";
        }
        return null;
    }

    @PutMapping("/register")
    public @ResponseBody
    Map register(@NotNull String nickname, @NotNull String email, @NotNull String password) {
        Map<String, String> message = new HashMap<>();
        User user = userMapper.findUserRegister(email);
        if (user == null) {
            userMapper.addUser(nickname, email, password);
            message.put("message","注册成功");
            return message;
        }
        message.put("message","注册失败");
        return message;
    }

    @GetMapping("/littlekitchen/user/{id}/info")
    public @ResponseBody
    Object getInfo(HttpSession session, @PathVariable("id") int id) {

        User user = userMapper.getInfoById(id);
        if (user != null) {
            if (Integer.parseInt(session.getAttribute("userid").toString())==id) {
                logger.info("用户"+session.getAttribute("userid").toString()+"查看自己的个人信息");
                return new InfoResponse(user.getNickname(), user.getEmail(), user.isGender(), user.getBirthday(), user.getPhoto(), user.getDescription(), true);
            }
            logger.info("用户"+session.getAttribute("userid").toString()+"查看用户"+id+"的个人信息");
            return new InfoResponse(user.getNickname(), user.getEmail(), user.isGender(), user.getBirthday(), user.getPhoto(), user.getDescription(), false);
        }
        return null;
    }

    @PostMapping("/littlekitchen/user/{id}/updateInfo")
    public @ResponseBody
    Map updateInfo(HttpSession session, @PathVariable("id") int id, String nickname, boolean gender, Date birthday, String photo, String description) {
        Map<String, String> message = new HashMap<>();
        try {
            userMapper.updateInfo(id, nickname, gender, birthday, photo, description);
        }catch (Exception e){
            message.put("message","更新失败");
            logger.info("更新个人信息失败！");
            return message;
        }
        message.put("message","更新成功");
        logger.info("更新个人信息成功！");
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
