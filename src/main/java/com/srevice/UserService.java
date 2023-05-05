package com.srevice;

import com.model.User;

import java.util.List;

public interface UserService {
    public int addUser(User user);
    public int addUserList(List<User> userList);
    public User login(String username);
    public int register(User user);
}
