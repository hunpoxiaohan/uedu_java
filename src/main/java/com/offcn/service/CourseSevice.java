package com.offcn.service;

import com.offcn.bean.Course;
import com.offcn.bean.CourseDetail;
import com.offcn.utils.PageUtils;

import java.util.List;

public interface CourseSevice {
    /**
     * 查询页面的所有数据
     * @param pageUtils
     * @param search
     * @return
     */
    List<Course> findByPage(PageUtils pageUtils, String search);

    int getTotalCount(String search);

    Course getByCourseName(String courseName);

    int updateByCid(Course course);

    int delCourse(String cids);

    int addCourse(Course course);

    List<Course> findAll();

    List<Course> findCourseByCourseType(int courseType, int count);

    List<Course> findByPage(PageUtils pageUtils , String courseType , String courseName);

    int getTotalCount(String courType,String courseName);

    Course findCourseByCid(int cid);

    Course findByCid(int cid);
}
