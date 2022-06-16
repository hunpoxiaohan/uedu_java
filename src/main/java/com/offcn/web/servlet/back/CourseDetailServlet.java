package com.offcn.web.servlet.back;

import com.alibaba.fastjson.JSON;
import com.offcn.bean.CourseDetail;
import com.offcn.service.CourseDetailService;
import com.offcn.service.impl.CourseDetailServiceImpl;
import com.offcn.utils.ResultVO;
import com.offcn.utils.SubFileNameUtils;
import com.offcn.web.servlet.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/detail")
public class CourseDetailServlet extends BaseServlet {

    private CourseDetailService courseDetailService = new CourseDetailServiceImpl();
    private ResultVO resultVO = null;

    /**
     * 添加课程详情
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void addDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        //1.接收请求参数
        Map<String, String[]> map = req.getParameterMap();
        //创建对象
        CourseDetail courseDetail = new CourseDetail();
        //使用Apache BeanUtils 简化对象的属性值的添加，将map中的数据添加到user对象中
        BeanUtils.populate(courseDetail, map);
        //System.out.println("courseDetail = " + courseDetail);

        /*
        由于前台页面传来的url字段和后台的对象字段名称完全一致
        所以BeanUtils其实已经将url封装进了对象
        但是，此时的url是一个地址，数据库只要文件名字
        因此我们还需要拿出这个url数据，改造后再放回
         */
        //url      http://localhost:8080/upload/asihjdlasfvhiasdhoifhoiashdiohoaihs131231231232.jpg
        //                                   ||
        //                                   \/
        //url           asihjdlasfvhiasdhoifhoiashdiohoaihs131231231232.jpg
        courseDetail.setUrl(SubFileNameUtils.subFileName(courseDetail.getUrl()));

        //2.执行添加操作
        int rows = courseDetailService.addDetail(courseDetail);

        if(rows > 0) {
            resultVO = new ResultVO(200, "添加成功", null);
        } else {
            resultVO = new ResultVO(500, "添加失败", null);
        }

        //3.结果对象转json响应回前端页面
        //使用fastjson工具将java对象转为json字符串
        String json = JSON.toJSONString(resultVO);

        resp.getWriter().print(json);
    }
}
