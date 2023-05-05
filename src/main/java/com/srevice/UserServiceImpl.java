package com.srevice;

import com.dao.UserDao;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int addUserList(List<User> userList) {
        return userDao.addUserList(userList);
    }

    @Override
    public User login(String username) {
        User user = userDao.selectByUsername(username);
        if(user != null) {
            return user;
        }
        else {
            user = userDao.selectByEmail(username);
            if(user != null) return user;
        }
        return null;
    }

    @Override
    public int register(User user) {
        if(userDao.selectByUsername(user.getUsername())!=null && userDao.selectByEmail(user.getEmail())!=null){
            return -1;
        }
        return userDao.register(user);
    }
}
