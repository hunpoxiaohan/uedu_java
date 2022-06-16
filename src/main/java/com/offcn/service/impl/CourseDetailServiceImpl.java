package com.offcn.service.impl;

import com.offcn.bean.CourseDetail;
import com.offcn.dao.CourseDetailDao;
import com.offcn.dao.impl.BaseDao;
import com.offcn.dao.impl.CourseDetailDaoImpl;
import com.offcn.service.CourseDetailService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDetailServiceImpl extends BaseDao<CourseDetail> implements CourseDetailService {

    private CourseDetailDao courseDetailDao = new CourseDetailDaoImpl();


    @Override
    public int addDetail(CourseDetail courseDetail) {
        String type = "";

        switch (courseDetail.getType()) {
            case "1":
                type = "第一章";
                break;
            case "2":
                type = "第二章";
                break;
            case "3":
                type = "第三章";
                break;
        }

        courseDetail.setType(type);

        return courseDetailDao.addDetail(courseDetail);
    }

    @Override
    public Map<String, List<CourseDetail>> findDetail(String cid) {
        return courseDetailDao.findDetail(cid);
    }


}
