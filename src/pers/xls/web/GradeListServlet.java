package pers.xls.web;

import pers.xls.bean.Grade;
import pers.xls.service.GradeService;
import pers.xls.service.impl.GradeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-25 23:09
 * @Modified by :
 */
@WebServlet(urlPatterns = "/Educational/student/getGradeList")
public class GradeListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数

        //2.调用Service方法
        GradeService gradeService = new GradeServiceImpl();
        List<Grade> gradeList = gradeService.getList();
        //3.跳转页面
        req.setAttribute("gradeList",gradeList);
        req.getRequestDispatcher("add.jsp").forward(req,resp);
    }
}
