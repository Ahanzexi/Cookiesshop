package com.controller;

import com.model.User;
import com.srevice.UserService;
import com.util.POIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class FileController {
    @Autowired
    private UserService userService;

    @RequestMapping("/importExcel")
    public String importExcel(@RequestParam("file") MultipartFile file) throws Exception{
        String filename = file.getOriginalFilename();
        List<User> userList= POIUtil.importExcel(file.getInputStream(),filename);
//      1、循环调用添加的方法把数据加入数据库；2、把SQL语句改成批量添加
//        for(User user:userList){
//            userService.addUser(user);
//        }
        userService.addUserList(userList);
        return "/admin/success";
    }
}
