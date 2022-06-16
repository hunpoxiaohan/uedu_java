package com.offcn.dao;

import com.offcn.bean.CourseUser;
import com.offcn.utils.PageUtils;

import java.util.List;

public interface CourseUserDao {

    /**
     * 分页模糊查询
     *
     * @param pageUtils
     * @param uids
     * @return
     */
    List<CourseUser> findByPage(PageUtils pageUtils, String uids);

    /**
     * 根据用户的uid查询符合搜索条件的记录数
     *
     * @return
     */
    int getTotalCount(String uids);


    int updateCourseUser(int cid ,int id);

    int delCourseUser(String ids);

    int addCourse(Integer cid, Integer uid);

    List<CourseUser> findByUid(int uid);

    int getTotalCount(String uid, String courseName);

    List<CourseUser> findByPageCourse(PageUtils<CourseUser> pageUtils ,String uid);
}
