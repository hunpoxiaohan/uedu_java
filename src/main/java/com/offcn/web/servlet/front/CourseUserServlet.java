package com.offcn.web.servlet.front;

import com.alibaba.fastjson.JSON;
import com.offcn.bean.CourseUser;
import com.offcn.bean.Order;
import com.offcn.service.CourseUserService;
import com.offcn.service.impl.CourseUserServiceImpl;
import com.offcn.utils.PageUtils;
import com.offcn.utils.ResultVO;
import com.offcn.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/frontCourseUser")
public class CourseUserServlet extends BaseServlet {

    private CourseUserService courseUserService = new CourseUserServiceImpl();
    private ResultVO resultVO = new ResultVO();

    public void toPay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String courseName = req.getParameter("courseName");
        String price = req.getParameter("price");
        String cid = req.getParameter("cid");
        String uid = req.getParameter("uid");

        String orderId = UUID.randomUUID().toString() + System.currentTimeMillis();

        HttpSession session = req.getSession();

        Order order = new Order(orderId, courseName, Double.parseDouble(price), Integer.parseInt(cid), Integer.parseInt(uid));
        System.out.println("order = " + order);
        session.setAttribute("order",order);

        resp.sendRedirect("testPay/index.jsp");
    }

    public void addCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Order order = (Order) session.getAttribute("order");
        System.out.println("order = " + order);

        int row = courseUserService.addCourse(order.getCid(), order.getUid());
        System.out.println("row = " + row);

        if(row > 0) {
            resp.getWriter().print("<script>alert('购买成功');location.href = 'http://127.0.0.1:5500/pages/course.html'</script>");
        } else {
            resp.getWriter().print("<script>alert('购买失败');");
        }
    }

    public void findByUid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uid = req.getParameter("uid");
        String pageSize = req.getParameter("pageSize");
        String currentPage = req.getParameter("currentPage");
        String courseName = req.getParameter("courseName");
        //System.out.println("courseName = " + courseName);
        //System.out.println("currentPage = " + currentPage);
        //System.out.println("pageSize = " + pageSize);
        //System.out.println("uid = " + uid);

        //获取条数
        int count = courseUserService.getTotalCount(uid,courseName);
        //System.out.println("count = " + count);
        PageUtils<CourseUser> pageUtils = new PageUtils(count, Integer.parseInt(pageSize), currentPage);
        List<CourseUser> list = courseUserService.findByPageCourse(pageUtils, courseName , uid);

        pageUtils.setList(list);
        //System.out.println("pageUtils = " + pageUtils);

        resultVO = new ResultVO(200,"查询成功",pageUtils);
        //
        //String json = JSON.toJSONString(resultVO);
        //resp.getWriter().print(json);

        //List<CourseUser> list1 = courseUserService.findByUid(Integer.parseInt(uid));

        //resultVO = new ResultVO(200,"获取成功",list);
        //
        resp.getWriter().print(JSON.toJSONString(resultVO));

    }
}
