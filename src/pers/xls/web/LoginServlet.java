package pers.xls.web;

import pers.xls.bean.Users;
import pers.xls.service.UsersService;
import pers.xls.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-25 9:19
 * @Modified by :
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2.调取service
        UsersService usersService = new UsersServiceImpl();
        Users users = usersService.login(username,password);
        //3.跳转页面
        if(users == null){
            //弹窗提示登陆失败
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.print("<script>location.href='login.jsp';alert('用户名或密码不正确');</script>");
        }else{
            //跳转到主页面
            //保存用户信息
            HttpSession session = req.getSession();
            session.setAttribute("u1",users);
            resp.sendRedirect("index.jsp");
        }
    }
}
