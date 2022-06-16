package com.offcn.web.servlet.front;

import com.alibaba.fastjson.JSON;
import com.offcn.bean.Course;
import com.offcn.service.CourseSevice;
import com.offcn.service.impl.CourseSeviceIpml;
import com.offcn.utils.PageUtils;
import com.offcn.utils.ResultVO;
import com.offcn.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/frontCourse")
public class CourseServlet extends BaseServlet {

    private CourseSevice courseSevice = new CourseSeviceIpml();
    private ResultVO resultVO = new ResultVO();

    public void findCOurseByCourseType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseType = req.getParameter("courseType");
        String count = req.getParameter("count");

        List<Course> list = courseSevice.findCourseByCourseType(Integer.parseInt(courseType),Integer.parseInt(count));

        if(list != null) {
            resultVO = new ResultVO(200,"查询成功",list);
        } else {
            resultVO = new ResultVO(500,"查询失败",null);
        }
        String json = JSON.toJSONString(resultVO);
        resp.getWriter().print(json);
    }

    public void findByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageSize = req.getParameter("pageSize");
        String currentPage = req.getParameter("currentPage");
        String courseType = req.getParameter("courseType");
        String courseName = req.getParameter("courseName");
        //System.out.println("courseName = " + courseName);

        int count = courseSevice.getTotalCount(courseType,courseName);
        //System.out.println("count = " + count);
        PageUtils<Course> pageUtils = new PageUtils(count, Integer.parseInt(pageSize), currentPage);
        List<Course> list = courseSevice.findByPage(pageUtils, courseType, courseName);

        pageUtils.setList(list);

        resultVO = new ResultVO(200,"查询成功",pageUtils);

        String json = JSON.toJSONString(resultVO);
        resp.getWriter().print(json);
    }

    public void findCourseByCid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid = req.getParameter("cid");

        Course course = courseSevice.findCourseByCid(Integer.parseInt(cid));

        resultVO = new ResultVO(200,"查询成功",course);

        resp.getWriter().print(JSON.toJSONString(resultVO));
    }
}
