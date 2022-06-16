package com.offcn.service.impl;

import com.offcn.bean.CourseUser;
import com.offcn.dao.CourseDao;
import com.offcn.dao.CourseUserDao;
import com.offcn.dao.UserDao;
import com.offcn.dao.impl.CourseDaoImpl;
import com.offcn.dao.impl.CourseUserDaoImpl;
import com.offcn.dao.impl.UserDaoImpl;
import com.offcn.service.CourseUserService;
import com.offcn.utils.PageUtils;

import java.util.List;

public class CourseUserServiceImpl implements CourseUserService {

    private CourseUserDao courseUserDao = new CourseUserDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private CourseDao courseDao = new CourseDaoImpl();

    @Override
    public List<CourseUser> findByPage(PageUtils pageUtils, String uids) {
        List<CourseUser> list = courseUserDao.findByPage(pageUtils, uids);
        System.out.println("list = " + list);
        for (CourseUser courseUser : list) {
            //分别获取已经存在的cid和uid，从而查询出具体的课程名和用户名
            courseUser.setUser(userDao.findByUid(courseUser.getUid()));
            courseUser.setCourse(courseDao.findByCid(String.valueOf(courseUser.getCid())));
        }

        return list;
    }

    @Override
    public int getTotalCount(String uids) {
        return courseUserDao.getTotalCount(uids);
    }

    @Override
    public int updateCourseUser(int cid, int id) {
        return courseUserDao.updateCourseUser(cid,id);
    }

    @Override
    public int delCourseUser(String ids) {
        return courseUserDao.delCourseUser(ids);
    }

    @Override
    public int addCourse(Integer cid, Integer uid) {
        return courseUserDao.addCourse(cid,uid);
    }

    @Override
    public List<CourseUser> findByUid(int uid) {
        List<CourseUser> list = courseUserDao.findByUid(uid);

        for (CourseUser courseUser : list) {
            courseUser.setCourse(courseDao.findByCid(String.valueOf(courseUser.getCid())));
            courseUser.setUser(userDao.findByUid(courseUser.getUid()));
        }
        return list;
    }

    @Override
    public int getTotalCount(String uid, String courseName) {

        return courseUserDao.getTotalCount(uid,courseName);
    }

    @Override
    public List<CourseUser> findByPageCourse(PageUtils<CourseUser> pageUtils, String courseName , String uid) {
        List<CourseUser> list = courseUserDao.findByPageCourse(pageUtils,uid);
        for (CourseUser courseUser : list) {
            //分别获取已经存在的cid和uid，从而查询出具体的课程名和用户名
            //courseUser.setUser(userDao.findByUid(courseUser.getUid()));
            courseUser.setCourse(courseDao.findByCid(String.valueOf(courseUser.getCid())));
        }

        return list;
    }
}
