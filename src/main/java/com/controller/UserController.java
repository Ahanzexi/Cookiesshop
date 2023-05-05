package com.controller;

import com.model.User;
import com.srevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(String username, String password, Model model, HttpSession session){
        User user = userService.login(username);
        if(user != null){
            if(user.getPassword().equals(password)){
                session.setAttribute("user",user);
                return "user_center";
            }else{
                model.addAttribute("failMsg","用户名、邮箱或者密码错误，请重新登录！");
                return "user_login";
            }
        }else {
            model.addAttribute("failMsg","用户名、邮箱或者密码错误，请重新登录！");
            return "user_login";
        }
    }

    @RequestMapping("/register")
    public String register(User user,Model model){
        int flag = userService.register(user);
        if( flag > 0){
            model.addAttribute("msg","注册成功，请登录！");
            return "user_login";
        }else if(flag == -1){
            model.addAttribute("msg","用户名或邮箱已被注册，请重新填写！");
        }else {
            model.addAttribute("msg","注册失败！");
        }
        return "user_register";
    }
}
