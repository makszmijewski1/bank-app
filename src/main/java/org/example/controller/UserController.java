package org.example.controller;

import org.example.dao.UserDao;
import org.example.model.User;

public class UserController {
    UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(User user) {
        userDao.createUser(user);
    }

    public User getUser(long id) {
        return userDao.getUser(id);
    }

    public boolean updateUser (User tmpUser) {
       return userDao.updateUser(tmpUser);
    }

    public boolean deleteUser(long id) {
        return userDao.deleteUser(id);
    }

    public boolean isUserExist(long pesel) {return userDao.isUserExist(pesel);}

    public User getUserByPesel(long pesel) {return userDao.getUserByPesel(pesel);}
}
