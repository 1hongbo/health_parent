package com.zhang.dao;

import com.zhang.pojo.User;

public interface UserDao {
    public User findByUsername(String username);
}
