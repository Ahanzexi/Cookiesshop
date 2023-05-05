package com.dao;

import com.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    public int addUser(User user);
    public int addUserList(List<User> userList);
    public User selectByUsername(String username);
    public User selectByEmail(String email);
    public int register(User user);
}
