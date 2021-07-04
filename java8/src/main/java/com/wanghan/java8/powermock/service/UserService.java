package com.wanghan.java8.powermock.service;

import com.wanghan.java8.powermock.common.User;
import com.wanghan.java8.powermock.dao.LocalUserDao;
import com.wanghan.java8.powermock.dao.UserDao;

/**
 * @Author WangHan
 * @Create 2021/5/11 10:42 下午
 */
public class UserService {

    private UserDao userDao;

    public UserService() {
    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int getUser(){
        return userDao.get();
    }

    public void saveUser(User user){
        userDao.save(user);
    }

    public int getLocalUser(){
        LocalUserDao localUserDao = new LocalUserDao();
        return localUserDao.get();
    }

    public void saveLocalUser(User user){
        LocalUserDao localUserDao = new LocalUserDao();
        localUserDao.save(user);
    }
}
