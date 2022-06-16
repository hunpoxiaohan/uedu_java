package com.offcn.web.filter;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/*")
public class CrossFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //解决post请求中文乱码
        request.setCharacterEncoding("utf-8");
        //响应中文乱码处理
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 不使用*，自动适配跨域域名，避免携带Cookie时失效
		 //会获取前端请求地址，设置允许往该服务器返回响应信息
        String origin = request.getHeader("Origin");
        if(null != origin) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        }
        // 自适应所有自定义头，发送请求之前会先进行预检请求，判断服务器是否允许发送该请求
        // 把要判断的请求头获取，放在以下参数中设为允许
        String headers = request.getHeader("Access-Control-Request-Headers");
        if(null != origin) {
            response.setHeader("Access-Control-Allow-Headers", headers);
            response.setHeader("Access-Control-Expose-Headers", headers);
        }
         // 允许跨域的请求方法类型
        //设置所有的请求方法类型都可以跨域，例如get  post请求
        response.setHeader("Access-Control-Allow-Methods", "*");
         // 预检命令（OPTIONS）缓存时间，单位：秒
         //预检请求检测，检测后台服务器是否允许发送跨域请求，一个小时检测一次
        response.setHeader("Access-Control-Max-Age", "3600");
        // 明确许可客户端发送Cookie，不允许删除字段即可
        //允许前台往后台发送请求的时候，携带请求信息，例如cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
