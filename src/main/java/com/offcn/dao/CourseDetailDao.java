package com.offcn.dao;

import com.offcn.bean.CourseDetail;

import java.util.List;
import java.util.Map;

public interface CourseDetailDao {

    /**
     * 添加课程详情
     *
     * @param courseDetail
     * @return
     */
    int addDetail(CourseDetail courseDetail);

    List<CourseDetail> findByCid(int cid);

    Map<String , List<CourseDetail>> findDetail(String cid);
}
