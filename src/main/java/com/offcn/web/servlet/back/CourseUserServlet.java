package com.offcn.web.servlet.back;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.offcn.bean.CourseUser;
import com.offcn.bean.User;
import com.offcn.service.CourseUserService;
import com.offcn.service.UserService;
import com.offcn.service.impl.CourseUserServiceImpl;
import com.offcn.service.impl.UserServiceImpl;
import com.offcn.utils.PageUtils;
import com.offcn.utils.ResultVO;
import com.offcn.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cu")
public class CourseUserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();
    private CourseUserService courseUserService = new CourseUserServiceImpl();
    private ResultVO resultVO = null;

    public void findByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数
        String search = req.getParameter("search");
        String pageSize = req.getParameter("pageSize");
        String currentPage = req.getParameter("currentPage");


        //2.查询总记录数
        //查出用户uid，方便查询所关联的课程信息
        String uids = userService.findByNameLike(search);
        //查询记录数
        int rows = courseUserService.getTotalCount(uids);

        //3.创建分页工具对象
        PageUtils pageUtils = new PageUtils(rows, Integer.parseInt(pageSize), currentPage);

        //4.查询分页的数据结果
        List<CourseUser> list = courseUserService.findByPage(pageUtils, uids);

        //分析：由于后台查询出结果，要返回前台，前台还要将数据展示出来，
        // 因此不能只返回查询的数据库数据，还要返回诸如当前页码、总记录数、页面容量等内容
        //因此就又涉及到了分页工具，因为只有分页工具中才携带了诸如当前页码、总记录数、页面容量等内容
        //5.将分页查询的数据结果，也封装到分页工具中
        pageUtils.setList(list);

        //6.封装响应数据
        resultVO = new ResultVO(200, "查询成功", pageUtils);

        //7.将响应对象转json发送
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultVO);

        resp.getWriter().print(json);

    }

    public void updateCourseUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String cid = req.getParameter("cid");

        int row = courseUserService.updateCourseUser(Integer.parseInt(cid), Integer.parseInt(id));

        if(row > 0) {
            resultVO = new ResultVO(200,"修改成功",null);
        } else {
            resultVO = new ResultVO(500,"修改失败",null);
        }

        String json = JSON.toJSONString(resultVO);
        resp.getWriter().print(json);
    }

    public void delCourseUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("ids");

        int i = courseUserService.delCourseUser(ids);

        if(i > 0) {
            resultVO = new ResultVO(200,"删除成功",null);
        } else {
            resultVO = new ResultVO(500,"删除失败",null);
        }
        String json = JSON.toJSONString(resultVO);
        resp.getWriter().print(json);
    }
}
