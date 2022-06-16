package com.offcn.dao.impl;

import com.offcn.bean.CourseUser;
import com.offcn.dao.CourseUserDao;
import com.offcn.utils.PageUtils;

import java.util.List;

public class CourseUserDaoImpl extends BaseDao<CourseUser> implements CourseUserDao {

    @Override
    public List<CourseUser> findByPage(PageUtils pageUtils, String uids) {
        StringBuffer sqlBuffer = new StringBuffer("select * from course_user");

        if(uids != null && !"".equals(uids)) {
            sqlBuffer.append(" where uid in(" + uids + ")");
        }

        sqlBuffer.append("limit ?,?");

        return queryList(CourseUser.class, sqlBuffer.toString(), pageUtils.getStartIndex(), pageUtils.getPageSize());
    }

    @Override
    public int getTotalCount(String uids) {
        StringBuffer sqlBuffer = new StringBuffer("select count(*) from course_user");

        if(uids != null && !"".equals(uids)) {
            sqlBuffer.append(" where uid in(" + uids + ")");
        }

        return Integer.parseInt(queryScale(sqlBuffer.toString()).toString());
    }

    @Override
    public int updateCourseUser(int cid, int id) {
        String sql = "update course_user set cid = ? where id = ?";
        return update(sql,cid,id);
    }

    @Override
    public int delCourseUser(String ids) {
        String sql = "delete from course_user where id in ( "+ ids +" )";
        return update(sql);
    }

    @Override
    public int addCourse(Integer cid, Integer uid) {
        String sql = "insert into course_user(cid,uid) values(?,?)";
        return update(sql,cid,uid);
    }

    @Override
    public List<CourseUser> findByUid(int uid) {
        String sql = "select * from course_user where uid = ?";
        return queryList(CourseUser.class,sql,uid);
    }

    @Override
    public int getTotalCount(String uid, String courseName) {
        String sql = "select count(*) from course_user where uid=?";
        return Integer.parseInt(queryScale(sql,uid).toString());
    }

    @Override
    public List<CourseUser> findByPageCourse(PageUtils<CourseUser> pageUtils ,String uid) {
        StringBuffer sqlBuffer = new StringBuffer("select * from course_user where uid = ?");

        sqlBuffer.append(" limit ?,?");

        return queryList(CourseUser.class, sqlBuffer.toString(),uid, pageUtils.getStartIndex(), pageUtils.getPageSize());
    }
}
