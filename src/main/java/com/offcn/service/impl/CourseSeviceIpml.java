package com.offcn.service.impl;

import com.offcn.bean.Course;
import com.offcn.bean.CourseDetail;
import com.offcn.dao.CourseDao;
import com.offcn.dao.CourseDetailDao;
import com.offcn.dao.impl.CourseDaoImpl;
import com.offcn.dao.impl.CourseDetailDaoImpl;
import com.offcn.service.CourseSevice;
import com.offcn.utils.PageUtils;

import java.util.List;

public class CourseSeviceIpml implements CourseSevice {
    private CourseDao courseDao =  new CourseDaoImpl();
    private CourseDetailDao courseDetailDao = new CourseDetailDaoImpl();

    @Override
    public List<Course> findByPage(PageUtils pageUtils, String search) {
        return courseDao.findByPage(pageUtils, search);
    }

    @Override
    public int getTotalCount(String search) {
        return courseDao.getTotalCount(search);
    }

    @Override
    public Course getByCourseName(String courseName) {
        return courseDao.getByCourseName(courseName);
    }

    @Override
    public int updateByCid(Course course) {
        return courseDao.updateByCid(course);
    }

    @Override
    public int delCourse(String cids) {
        return courseDao.delCourse(cids);
    }

    @Override
    public int addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public List<Course> findCourseByCourseType(int courseType, int count) {
        return courseDao.findCourseByCourseType(courseType,count);
    }

    @Override
    public List<Course> findByPage(PageUtils pageUtils, String courseType, String courseName) {
        return courseDao.findByPage(pageUtils, courseType, courseName);
    }

    @Override
    public int getTotalCount(String courType, String courseName) {
        return courseDao.getTotalCount(courType, courseName);
    }

    @Override
    public Course findCourseByCid(int cid) {
        Course course = courseDao.findCourseByCid(cid);
        List<CourseDetail> list = courseDetailDao.findByCid(cid);
        course.setDetailList(list);
        return course;
    }

    @Override
    public Course findByCid(int cid) {
        Course courseByCid = courseDao.findCourseByCid(cid);
        List<CourseDetail> list = courseDetailDao.findByCid(cid);
        courseByCid.setDetailList(list);
        return courseByCid;
    }
}
