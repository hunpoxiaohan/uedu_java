package com.offcn.web.servlet.back;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.offcn.bean.User;
import com.offcn.service.UserService;
import com.offcn.service.impl.UserServiceImpl;
import com.offcn.utils.PageUtils;
import com.offcn.utils.ResultVO;
import com.offcn.web.servlet.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();
    private ResultVO resultVO = null;

    /**
     * 
     * 登录验证
     * @param req 
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String imageCode = req.getParameter("imageCode");


        HttpSession session = req.getSession();
        String code = (String)session.getAttribute("verCode");

        if(code.equalsIgnoreCase(imageCode)) {

            User user = userService.findByUsernameAndPassword(username, password);

            if(user != null) {
                resultVO = new ResultVO(200,"登录成功",user);
            } else {
                resultVO = new ResultVO(500,"用户名或密码不正确",null);
            }

        } else {
            resultVO = new ResultVO(500, "验证码不正确", null);
        }

        //将响应对象转为json进行回传
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultVO);

        resp.getWriter().print(json);
    }

    public void userInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");

        User user = userService.findByUid(Integer.parseInt(uid));


        if(user != null) {
            resultVO = new ResultVO(200, "查询成功", user);
        } else {
            resultVO = new ResultVO(500, "查询失败", null);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultVO);

        resp.getWriter().print(json);
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        session.invalidate();
        resultVO = new ResultVO(200, "退出成功", null);


        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultVO);

        resp.getWriter().print(json);
    }

    public void getUserList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        String pageSize = req.getParameter("pageSize");
        String currentPage = req.getParameter("currentPage");

        int totalCount = userService.getTotalCount(search);

        PageUtils pageUtils = new PageUtils(totalCount, Integer.parseInt(pageSize), currentPage);

        List<User> list = userService.findByPage(pageUtils, search);

        pageUtils.setList(list);

        resultVO = new ResultVO(200,"查询成功",pageUtils);

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(resultVO);
        resp.getWriter().print(s);
    }

    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = req.getParameterMap();
        //创建对象
        User user = new User();
        //使用Apache BeanUtils 简化对象的属性值的添加，将map中的数据添加到user对象中
        BeanUtils.populate(user, map);

        User byUsername = userService.findByUsername(user.getUsername());

        if(byUsername == null) {
            int num = userService.addUser(user);
            if(num > 0) {
                resultVO = new ResultVO(200, "添加成功", null);
            } else {
                resultVO = new ResultVO(500, "添加失败", null);
            }
        } else {
            resultVO = new ResultVO(100, "用户名已存在", null);
        }

        //3.结果对象转json响应回前端页面
        //使用fastjson工具将java对象转为json字符串
        String json = JSON.toJSONString(resultVO);

        resp.getWriter().print(json);
    }

    public void deletAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {

        String users = req.getParameter("uids");
        //for (String user : users) {
        //    System.out.println("user = " + user);
        //}
        int deleteAll = userService.deleteAll(users);

        if(deleteAll > 0) {
            resultVO = new ResultVO(200, "删除成功，删除了"+ deleteAll +"条数据", null);
        } else {
            resultVO = new ResultVO(500, "删除失败", null);
        }

        String json = JSON.toJSONString(resultVO);

        resp.getWriter().print(json);
    }

    public void getByusername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {

        String username = req.getParameter("username");
        User user = userService.findByUsername(username);
        //System.out.println(user.getName());

        if (user != null) {
            resultVO = new ResultVO(200,"查询成功",user);
        } else {
            resultVO = new ResultVO(500,"查询失败",null);
        }

        String s = JSON.toJSONString(resultVO);

        resp.getWriter().print(s);
    }
    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {

        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        BeanUtils.populate(user, map);

        int i = userService.updateUser(user);

        if(i > 0) {
            resultVO = new ResultVO(200,"修改成功",null);
        } else {
            resultVO = new ResultVO(500,"修改失败",null);
        }
        String s = JSON.toJSONString(resultVO);
        resp.getWriter().print(s);
    }

}
