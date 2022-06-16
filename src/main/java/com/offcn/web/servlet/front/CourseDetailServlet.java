package com.offcn.web.servlet.front;

import com.alibaba.fastjson.JSON;
import com.offcn.bean.CourseDetail;
import com.offcn.service.CourseDetailService;
import com.offcn.service.impl.CourseDetailServiceImpl;
import com.offcn.utils.ResultVO;
import com.offcn.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet("/courseDetail")
public class CourseDetailServlet extends BaseServlet  {
    private CourseDetailService courseDetailService = new CourseDetailServiceImpl();
    private ResultVO resultVO = new ResultVO();


    public void findDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cid = req.getParameter("cid");
        //System.out.println("cid = " + cid);
        Map<String, List<CourseDetail>> map = courseDetailService.findDetail(cid);
        //System.out.println("map = " + map);

        resultVO = new ResultVO(200,"查询成功",map);

        resp.getWriter().print(JSON.toJSONString(resultVO));
    }
}
