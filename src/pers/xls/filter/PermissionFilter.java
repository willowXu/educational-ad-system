package pers.xls.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-26 18:48
 * @Modified by :
 */
public class PermissionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object user = request.getSession().getAttribute("u1");
        String uri = request.getRequestURI();
        System.out.println("uri:"+uri);
        if(!uri.endsWith("login.jsp")&&!uri.endsWith("login")){//不是登录页面
            if(user==null) {
                System.out.println("没有权限");
                response.sendRedirect("login.jsp");
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
