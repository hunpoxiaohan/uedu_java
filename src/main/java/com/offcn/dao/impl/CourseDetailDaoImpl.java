package com.offcn.dao.impl;

import com.offcn.bean.CourseDetail;
import com.offcn.dao.CourseDetailDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDetailDaoImpl extends BaseDao<CourseDetail> implements CourseDetailDao {

    @Override
    public int addDetail(CourseDetail courseDetail) {
        String sql = "insert into coursedetail(name, type, url, start_data, cid) values(?,?,?,?,?)";
        return update(sql,
                courseDetail.getName(),
                courseDetail.getType(),
                courseDetail.getUrl(),
                courseDetail.getStart_data(),
                courseDetail.getCid()
        );
    }

    @Override
    public List<CourseDetail> findByCid(int cid) {
        String sql = "select * from coursedetail where cid = ?";
        return queryList(CourseDetail.class,sql,cid);
    }

    @Override
    public Map<String, List<CourseDetail>> findDetail(String cid) {
        Map<String, List<CourseDetail>> map = new HashMap<>();

        String sql = " select * from coursedetail where cid = ? group by type";

        List<CourseDetail> types = queryList(CourseDetail.class,sql,cid);

        List<String> typeNames = new ArrayList<>();

        for (CourseDetail type : types) {
            typeNames.add(type.getType());
        }

        for (String typeName : typeNames) {
            sql = "select * from coursedetail where cid = ? and type = ?";

            List<CourseDetail> details = queryList(CourseDetail.class,sql,cid,typeName);

            map.put(typeName,details);
        }
        return map;
    }
}
