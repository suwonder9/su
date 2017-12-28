package com.wonder.service;

import com.wonder.dao.UserDao;
import com.wonder.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserEntity getUser(String account){
        return userDao.findByAccount(account);
    }

    public void addUser(String account ,String password){
        UserEntity user = new UserEntity();
        user.setAccount(account);
        user.setPassword(password);
        userDao.save(user);
    }

    public List<UserEntity> userList(){
        return userDao.findAll();
    }

}
