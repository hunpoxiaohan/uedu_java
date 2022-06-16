package com.offcn.dao;

import com.offcn.bean.Course;
import com.offcn.utils.PageUtils;

import java.util.List;

public interface CourseDao {
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

    /**
     * 根据cid查询课程信息
     *
     * @param cid
     * @return
     */
    Course findByCid(String cid);

    List<Course> findAll();

    List<Course> findCourseByCourseType(int courseType, int count);

    List<Course> findByPage(PageUtils pageUtils , String courseType , String courseName);

    int getTotalCount(String courType,String courseName);

    Course findCourseByCid(int cid );
}
