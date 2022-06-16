package com.offcn.service;

import com.offcn.bean.CourseDetail;

import java.util.List;
import java.util.Map;

public interface CourseDetailService {

    /**
     * 添加课程详情
     *
     * @param courseDetail
     * @return
     */
    int addDetail(CourseDetail courseDetail);

    Map<String , List<CourseDetail>> findDetail(String cid);
}
