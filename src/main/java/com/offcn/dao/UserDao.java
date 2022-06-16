package com.offcn.dao;

import com.offcn.bean.User;
import com.offcn.utils.PageUtils;

import java.util.List;

public interface UserDao {

    /**
     * 根据账号密码查询
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);

    /**
     * 根据uid查询用户
     * @param uid
     * @return
     */
    User findByUid(int uid);

    /**
     * 分页模糊查询
     *
     * @param pageUtils
     * @param search
     * @return
     */
    List<User> findByPage(PageUtils pageUtils, String search);

    /**
     * 获取符合条件的数据的总记录数
     *
     * @return
     */
    int getTotalCount(String search);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据账号名查询
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 删除选中的
     * @param username
     * @return
     */
    int deleteAll(String username);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 根据用户关键字搜索用户信息
     *
     * @param search
     * @return
     */
    List<User> findByNameLike(String search);


    User checkPhone(String phone);

    User findByPhoneAndPassword(String phone, String password);
}
