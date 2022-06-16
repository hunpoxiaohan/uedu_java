package com.offcn.dao.impl;

import com.offcn.bean.Course;
import com.offcn.bean.User;
import com.offcn.dao.CourseDao;
import com.offcn.utils.PageUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CourseDaoImpl extends BaseDao<Course> implements CourseDao {
    @Override
    public List<Course> findByPage(PageUtils pageUtils, String search) {
        StringBuffer sqlBuffer = new StringBuffer("select * from course");

        if(!("".equals(search)) && search != null) {
            sqlBuffer.append(" where courseName like '%" + search + "%'");
        }

        sqlBuffer.append(" limit ?, ?");
        return queryList(Course.class, sqlBuffer.toString(), pageUtils.getStartIndex(), pageUtils.getPageSize());
    }

    @Override
    public int getTotalCount(String search) {
        StringBuffer sqlBuffer = new StringBuffer("select count(*) from course");

        if(!("".equals(search)) && search != null) {
            sqlBuffer.append(" where courseName like '%" + search + "%'");
        }
        return Integer.parseInt(queryScale(sqlBuffer.toString()).toString());
    }

    @Override
    public Course getByCourseName(String courseName) {
        String sql = "select * from course where courseName = ?";
        Course course = queryOne(Course.class, sql, courseName);
        return course;
    }

    @Override
    public int updateByCid(Course course) {

        String sql = "UPDATE `course` SET `courseName` = ? , descs = ? , courseType = ?,courseImage = ?,courseVideo = ?,`coursePrice` = ?,`status` = ?,createTime = ?  where cid = ?";

        int update = update(sql, course.getCourseName(), course.getDescs(), course.getCourseType(), course.getCourseImage(), course.getCourseVideo(), course.getCoursePrice(), course.getStatus(), course.getCreateTime(), course.getCid());

        return update;
    }

    /**
     * @param cids 根据cid删除
     * @return
     */
    @Override
    public int delCourse(String cids) {
        String sql = "delete from course where cid in(" + cids + ")";
        int update = update(sql);
        return update;
    }

    @Override
    public int addCourse(Course course) {
        String sql = "insert into course(courseName, descs, courseType, courseImage, courseVideo, coursePrice, status, createTime) values(?,?,?,?,?,?,?,?)";
        int add = update(sql, course.getCourseName(), course.getDescs(), course.getCourseType(), course.getCourseImage(), course.getCourseVideo(), course.getCoursePrice(), course.getStatus(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return add;
    }

    @Override
    public Course findByCid(String cid) {
        String sql = "select * from course where cid = ?";
        return queryOne(Course.class, sql, cid);
    }

    @Override
    public List<Course> findAll() {
        String sql = "select * from course";
        return queryList(Course.class, sql);
    }

    @Override
    public List<Course> findCourseByCourseType(int courseType, int count) {
        String sql = "select * from course where courseType = ? limit ?";
        return queryList(Course.class,sql,courseType,count);
    }

    @Override
    public List<Course> findByPage(PageUtils pageUtils, String courseType, String courseName) {
        StringBuffer sqlBuffer = new StringBuffer("select * from course where 1 = 1");
        //String sql = "select * from course where courseType = ? and courName like '%?%' , limit 0,6";

        if(courseType != null && !"".equals(courseType)) {
            sqlBuffer.append(" and courseType = " + courseType);
        }
        if(courseName != null && !"".equals(courseName)) {
            sqlBuffer.append(" and courseName like '%" + courseName + "%'");
        }
        sqlBuffer.append(" limit ? ,? ");
        //System.out.println("sqlBuffer = " + sqlBuffer);
        return queryList(Course.class , sqlBuffer.toString(),pageUtils.getStartIndex(),pageUtils.getPageSize());
    }

    @Override
    public int getTotalCount(String courseType, String courseName) {
        StringBuffer sqlBuffer = new StringBuffer("select count(*) from course where 1 = 1");

        if(courseType != null && !"".equals(courseType)) {
            sqlBuffer.append(" and courseType = " + courseType);
        }
        if(courseName != null && !"".equals(courseName)) {
            sqlBuffer.append(" and courseName like '%" + courseName + "%'");
        }
        //System.out.println("sqlBuffer = " + sqlBuffer);
        return Integer.parseInt(queryScale(sqlBuffer.toString()).toString());
    }

    @Override
    public Course findCourseByCid(int cid) {
        String sql = "select * from course where cid = ?";
        return queryOne(Course.class,sql,cid);
    }
}
