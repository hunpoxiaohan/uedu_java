package com.offcn.service.impl;

import com.offcn.bean.User;
import com.offcn.dao.UserDao;
import com.offcn.dao.impl.UserDaoImpl;
import com.offcn.service.UserService;
import com.offcn.utils.PageUtils;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User result = userDao.findByUsernameAndPassword(username, password);
        return result;
    }

    @Override
    public User findByUid(int uid) {
        User byUid = userDao.findByUid(uid);
        return byUid;
    }

    @Override
    public List<User> findByPage(PageUtils pageUtils, String search) {
        return userDao.findByPage(pageUtils, search);
    }

    @Override
    public int getTotalCount(String search) {
        return userDao.getTotalCount(search);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public int deleteAll(String username) {
        return userDao.deleteAll(username);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public String findByNameLike(String search) {
        StringBuffer bufferUids = new StringBuffer();

        List<User> list = userDao.findByNameLike(search);

        for (User user : list) {
            bufferUids.append(user.getUid()).append(",");
        }

        String uids = bufferUids.substring(0, bufferUids.length() - 1);
        return uids;
    }

    @Override
    public boolean checkPhone(String phone) {
        return userDao.checkPhone(phone) == null ? true : false;
    }

    @Override
    public User findByPhoneAndPassword(String phone, String password) {
        return userDao.findByPhoneAndPassword(phone,password);
    }
}
