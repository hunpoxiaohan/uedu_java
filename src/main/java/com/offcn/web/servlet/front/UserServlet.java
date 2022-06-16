package com.offcn.web.servlet.front;

import com.alibaba.fastjson.JSON;
import com.offcn.bean.User;
import com.offcn.service.UserService;
import com.offcn.service.impl.UserServiceImpl;
import com.offcn.utils.ResultVO;
import com.offcn.web.servlet.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/frontUser")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();
    private ResultVO resultVO = new ResultVO();

    public void checkPhone(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");

        if(userService.checkPhone(phone)) {
            resultVO = new ResultVO(200,"手机号不存在",null);
        } else {
            resultVO = new ResultVO(500,"手机号已存在",null);
        }

        String json = JSON.toJSONString(resultVO);
        resp.getWriter().print(json);
    }

    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {

        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        BeanUtils.populate(user,map);

        user.setSex(0);
        user.setAge(20);
        user.setStatus(1);
        user.setRole(2);

        int i = userService.addUser(user);

        if(i > 0) {
            resultVO = new ResultVO(200,"添加成功",null);
        } else {
            resultVO =  new ResultVO(500,"添加失败",null);
        }

        String json = JSON.toJSONString(resultVO);
        resp.getWriter().print(json);
    }


    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        User user = userService.findByPhoneAndPassword(phone, password);
        if(user == null) {
            resultVO = new ResultVO(500,"添加失败",null);
        } else {
            resultVO = new ResultVO(200,"添加成功",user);
        }

        String json = JSON.toJSONString(resultVO);
        resp.getWriter().print(json);
    }
}
