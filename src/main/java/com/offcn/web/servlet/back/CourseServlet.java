package com.offcn.web.servlet.back;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.offcn.bean.Course;
import com.offcn.bean.User;
import com.offcn.service.CourseSevice;
import com.offcn.service.UserService;
import com.offcn.service.impl.CourseSeviceIpml;
import com.offcn.service.impl.UserServiceImpl;
import com.offcn.utils.PageUtils;
import com.offcn.utils.ResultVO;
import com.offcn.utils.SubFileNameUtils;
import com.offcn.web.servlet.BaseServlet;
import com.sun.media.sound.WaveExtensibleFileReader;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/course")
@MultipartConfig
public class CourseServlet extends BaseServlet {
    private CourseSevice courseSevice = new CourseSeviceIpml();
    private ResultVO resultVO = null;

    public void getCourseList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        String pageSize = req.getParameter("pageSize");
        String currentPage = req.getParameter("currentPage");

        int totalCount = courseSevice.getTotalCount(search);

        PageUtils pageUtils = new PageUtils(totalCount, Integer.parseInt(pageSize), currentPage);

        List<Course> list = courseSevice.findByPage(pageUtils, search);
        pageUtils.setList(list);

        resultVO = new ResultVO(200,"查询成功",pageUtils);

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(resultVO);
        resp.getWriter().print(s);
    }

    public void getByCourseName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseName = req.getParameter("courseName");

        Course byCourseName = courseSevice.getByCourseName(courseName);

        if (byCourseName != null) {
            resultVO = new ResultVO(200,"查询成功",byCourseName);
        } else {
            resultVO = new ResultVO(500,"查询失败",null);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(resultVO);
        resp.getWriter().print(s);
    }

    /**
     * 更新的方法
     */
    public void updateCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = req.getParameterMap();
        //创建对象
        Course course = new Course();
        //使用Apache BeanUtils 简化对象的属性值的添加，将map中的数据添加到user对象中
        BeanUtils.populate(course, map);

        System.out.println("course = " + course);

        int i = courseSevice.updateByCid(course);
        if (i > 0) {
            resultVO = new ResultVO(200,"修改成功",null);
        } else {
            resultVO = new ResultVO(500,"修改失败",null);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(resultVO);
        resp.getWriter().print(s);
    }

    public void delCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String cids = req.getParameter("cids");
        String del = req.getParameter("del");
        String[] arr = del.split(",");

        //System.out.println("cids = " + cids);

        int i = courseSevice.delCourse(cids);

        if(i > 0) {
            resultVO = new ResultVO(200,"删除成功,删除了"+ i + "条数据",null);

            String directoryPath = "E:/upload/";

            for (String s : arr) {
                //3.根据文件路径删除文件
                System.out.println("s = " + s);
                File file = new File(directoryPath,s);
                file.delete();
            }

            //3.根据文件路径删除文件
            //File fileObj1 = new File(imageFilePath);
            //File fileObj = new File(videoFilePath);
            //fileObj.delete();
            //fileObj1.delete();
        } else {
            resultVO = new ResultVO(500,"删除失败",null);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(resultVO);
        resp.getWriter().print(s);
    }

    public void uploadFile(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {

        //上传的文件
        Part part = req.getPart("file");

        String fileName = part.getSubmittedFileName();

        fileName = "" + UUID.randomUUID().toString() + System.currentTimeMillis() + fileName;

        File path = new File("E:\\upload");
        //路径是否存在
        if(path.exists()) {
            path.mkdirs();
        }

        File savePath = new File(path, fileName);

        part.write(savePath.toString());

        String backPath = "E:\\upload\\" + fileName;

        if(fileName.contains(".jpg") || fileName.contains(".jepg") || fileName.contains(".gif") || fileName.contains(".png")) {
            resultVO = new ResultVO(1,"图片上传成功",backPath);
        } else {
            resultVO = new ResultVO(2,"视频上传成功",backPath);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(resultVO);
        resp.getWriter().print(s);
    }

    public void removeFile(HttpServletRequest req ,HttpServletResponse resp) throws IOException,ServletException {
        String fileURL = req.getParameter("fileURL");
        //System.out.println("fileURL = " + fileURL);

        String directoryPath = "E:/upload/";

        String filePath = directoryPath + fileURL;

        System.out.println("filePath = " + filePath);
        //3.根据文件路径删除文件
        File fileObj = new File(filePath);
        fileObj.delete();

        //4.判断删除的东西是图片还是视频，之后封装对应的结果集
        if(filePath.contains(".jpg") || filePath.contains(".jpeg") || filePath.contains(".gif") || filePath.contains(".png")) {
            //图片
            //封装结果集
            resultVO = new ResultVO(1, "图片删除成功", filePath);
        } else {
            //视频
            resultVO = new ResultVO(2, "视频删除成功", filePath);
        }

        //5.回传相应
        String json = JSON.toJSONString(resultVO);
        resp.getWriter().print(json);
    }

    public void addCourse(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = req.getParameterMap();
        //创建对象
        Course course = new Course();
        //使用Apache BeanUtils 简化对象的属性值的添加，将map中的数据添加到user对象中
        BeanUtils.populate(course, map);

        System.out.println("course = " + course);
        String dialogImageUrl = req.getParameter("dialogImageUrl");
        String dialogVideoUrl = req.getParameter("dialogVedioUrl");

        course.setCourseImage(SubFileNameUtils.subFileName(dialogImageUrl));
        course.setCourseVideo(SubFileNameUtils.subFileName(dialogVideoUrl));

        int rows = courseSevice.addCourse(course);

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

    public void findAll(HttpServletRequest req , HttpServletResponse resp) throws IOException, ServletException {
        List<Course> list = courseSevice.findAll();

        resultVO = new ResultVO(200,"查询成功",list);

        String json = JSON.toJSONString(resultVO);
        resp.getWriter().print(json);
    }
}
