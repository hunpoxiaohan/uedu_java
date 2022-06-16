package com.offcn.dao.impl;

import com.offcn.bean.User;
import com.offcn.dao.UserDao;
import com.offcn.utils.PageUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    /**
     * 根据账号密码查询是否存在，不存在为null，存在返回对象。
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "select * from user where username = ? AND password = ? AND status = 1 AND role = 1";
        User user = queryOne(User.class, sql, username, password);
        return user;
    }

    /**
     * 根据id查询信息，没有该id的时候返回null；
     * @param uid
     * @return
     */
    @Override
    public User findByUid(int uid) {
        String sql = "select * from user where uid = ?";
        User user = queryOne(User.class, sql, String.valueOf(uid));
        return user;
    }

    @Override
    public List<User> findByPage(PageUtils pageUtils, String search) {
        StringBuffer sqlBuffer = new StringBuffer("select * from user");

        if(!("".equals(search)) && search != null) {
            sqlBuffer.append(" where username like '%" + search + "%'");
        }
        sqlBuffer.append(" limit ?, ?");
        return queryList(User.class, sqlBuffer.toString(), pageUtils.getStartIndex(), pageUtils.getPageSize());
    }

    @Override
    public int getTotalCount(String search) {
        StringBuffer sqlBuffer = new StringBuffer("select count(*) from user");

        if(!("".equals(search)) && search != null) {
            sqlBuffer.append(" where username like '%" + search + "%'");
        }
        return Integer.parseInt(queryScale(sqlBuffer.toString()).toString());
    }

    @Override
    public int addUser(User user) {

        String sql = "insert into user(uid, name, phone, age, sex, username, password, status, createtime, role) values(?,?,?,?,?,?,?,?,?,?)";

        return update(sql,
                user.getUid(),
                user.getName(),
                user.getPhone(),
                user.getAge(),
                user.getSex(),
                user.getUsername(),
                user.getPassword(),
                user.getStatus(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                user.getRole()
        );
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM `user` where username = ?";
        User user = queryOne(User.class, sql, username);
        return user;
    }

    @Override
    public int deleteAll(String uids) {

        String sql = "DELETE from `user` where uid in (" + uids +")";

        int update = update(sql);
        return update;
    }

    @Override
    public int updateUser(User user) {

        String sql = "UPDATE `user` SET `name` = ? , phone = ? , age = ?,sex = ?,username = ?,`password` = ?,`status` = ?,role = ?  where uid = ?";


        int update = update(sql, user.getName(), user.getPhone(), user.getAge(), user.getSex(), user.getUsername(), user.getPassword(), user.getStatus(), user.getRole(), user.getUid());

        return update;

    }

    @Override
    public List<User> findByNameLike(String search) {
        String sql = "select * from user where name like '%" + search +  "%'";
        return queryList(User.class, sql);
    }

    @Override
    public User checkPhone(String phone) {
        String sql = "select * from user where phone = ?";
        return queryOne(User.class,sql,phone);
    }

    @Override
    public User findByPhoneAndPassword(String phone, String password) {
        String sql = "select * from user where phone = ? and password = ?";
        return queryOne(User.class,sql,phone,password);
    }
}
