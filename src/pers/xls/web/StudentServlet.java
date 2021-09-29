package pers.xls.web;

import pers.xls.bean.Grade;
import pers.xls.bean.Student;
import pers.xls.service.GradeService;
import pers.xls.service.StudentService;
import pers.xls.service.impl.GradeServiceImpl;
import pers.xls.service.impl.StudentServiceImpl;
import pers.xls.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-26 17:47
 * @Modified by :
 */
@WebServlet(urlPatterns = "/Educational/student/studentServlet")
public class StudentServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if("add".equals(method)){
            System.out.println("add");
            addStu(req,resp);
        }else if("delete".equals(method)){
            System.out.println("delete");
            deleteStuById(req,resp);
        }else if("findById".equals(method)){
            System.out.println("findById");
            findStuById(req,resp);
        }else if("update".equals(method)){
            System.out.println("update");
            updateStu(req,resp);
        }else if("existStuNo".equals(method)){
            System.out.println("check exist stuno");
            existStuNo(req,resp);
        }else {
            System.out.println("getList");
            getList(req, resp);
        }
    }

    //新增学生
    public void addStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数
        String stuName = req.getParameter("stuName");
        System.out.println("stuName:"+stuName);
        String stuNo = req.getParameter("stuNo");
        int sex = Integer.parseInt(req.getParameter("sex"));
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String registered = req.getParameter("registered");
        String address = req.getParameter("address");
        String profession = req.getParameter("profession");
        String idNumber = req.getParameter("idNumber");
        String politics = req.getParameter("politics");
        String introduction = req.getParameter("introduction");
        int gid = Integer.parseInt( req.getParameter("gid"));
        //2.调取service
        StudentService studentService = new StudentServiceImpl();
        //设置student的参数
        Student student = new Student();
        student.setStuName(stuName);
        student.setStuNo(stuNo);
        student.setSex(sex);
        student.setPhone(phone);
        student.setEmail(email);
        student.setRegistered(registered);
        student.setAddress(address);
        student.setProfession(profession);
        student.setIdNumber(idNumber);
        student.setPolitics(politics);
        student.setIntroduction(introduction);
        student.setGid(gid);
        int count = studentService.insertStudent(student);
        //3.跳转页面
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(count>0){//新增成功
            writer.println("<script>alert('新增成功！');location.href='/Educational/student/studentServlet'; </script>");
        }else{//失败
            writer.println("<script>alert('新增失败！');location.href='/Educational/student/getGradeList'; </script>");
        }
    }

    //删除学生
    public void deleteStuById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数
        int stuId = Integer.parseInt(req.getParameter("stuid"));
        //2.调用service方法
        StudentService studentService = new StudentServiceImpl();
        int count = studentService.deleteStudent(stuId);
        //3.跳转页面
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if (count > 0) {
            writer.println("<script> alert('删除成功');location.href='/Educational/student/studentServlet?'; </script>");
        }else{
            writer.println("<script> alert('删除失败');location.href='/Educational/student/studentServlet?'; </script>");
        }
    }

    //查询学生(根据学生编号)
    public void findStuById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数
        String s_id = req.getParameter("stuid");
        int stuId = Integer.parseInt(s_id);
        //2.调用service方法
        StudentService studentService = new StudentServiceImpl();
        Student student = studentService.findById(stuId);
        //查询年级列表
        GradeService gradeService = new GradeServiceImpl();
        List<Grade> gradeList = gradeService.getList();
        //3.跳转页面
        req.setAttribute("student",student);
        req.setAttribute("gradeList",gradeList);
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }

    //查询学生列表
    public void getList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数
        //1.1 模糊查询参数
        String stuname = req.getParameter("stuname");
        String stuno = req.getParameter("stuno");
        String strSex = req.getParameter("sex");
        int sex = -1;
        if(strSex!=null&&strSex!="") {
            sex = Integer.parseInt(strSex);
        }
        //1.2 分页参数 开始位置，(显示条数)
        String s_pageIndex = req.getParameter("pageIndex");
        int pageIndex = 1;
        if(s_pageIndex!=null){
            pageIndex = Integer.parseInt(s_pageIndex);
        }
        //2.调取Service方法
        PageUtil pageUtil = new PageUtil();
        StudentService studentService = new StudentServiceImpl();
        List<Student> studentList = studentService.getStudents(stuname,stuno,sex,pageIndex,pageUtil.getPageSize());
        //获取总页数=总条数/每页显示的条数 想上取整
        int total = studentService.total(stuname,stuno,sex);
        pageUtil.setTotal(total);
        //3.跳转页面
        pageUtil.setPageIndex(pageIndex);
        pageUtil.setDataList(studentList);
        //存储模糊查询的条件
        //存储分页条件
        req.setAttribute("p1",pageUtil);
        req.setAttribute("stuname",stuname);
        req.setAttribute("stuno",stuno);
        req.setAttribute("sex",sex);
        req.getRequestDispatcher("list.jsp").forward(req,resp);
    }

    //更新学生
    public void updateStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数
        int stuId = Integer.parseInt(req.getParameter("stuid"));
        String stuName = req.getParameter("stuname");
        String stuNo = req.getParameter("stuno");
        int sex = Integer.parseInt(req.getParameter("sex"));
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String registered = req.getParameter("registered");
        String address = req.getParameter("address");
        String profession = req.getParameter("profession");
        String idNumber = req.getParameter("idNumber");
        String politics = req.getParameter("politics");
        String introduction = req.getParameter("introduction");
        int gid = Integer.parseInt(req.getParameter("gid"));
        Student student = new Student();
        student.setStuId(stuId);
        student.setStuName(stuName);
        student.setStuNo(stuNo);
        student.setSex(sex);
        student.setPhone(phone);
        student.setEmail(email);
        student.setRegistered(registered);
        student.setAddress(address);
        student.setProfession(profession);
        student.setIdNumber(idNumber);
        student.setPolitics(politics);
        student.setIntroduction(introduction);
        student.setGid(gid);
        //2.调用Service方法
        StudentService studentService = new StudentServiceImpl();
        int count = studentService.updateStudent(student);
        //3.跳转页面
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if (count > 0) {
            writer.println("<script> alert('修改成功');location.href='/Educational/student/studentServlet'; </script>");
        }else{
            writer.println("<script> alert('修改失败');location.href='/Educational/student/studentServlet?" +
                    "method=findById&stuId='"+stuId+"; </script>");
        }
    }

    public void existStuNo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stuNo = req.getParameter("stuNo");
        StudentService studentService = new StudentServiceImpl();
        boolean result = studentService.findByStuNo(stuNo);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(result){//已经存在
            System.out.println("存在！");
            writer.println("学号已存在！");
        }else{//不存在
            System.out.println("不存在");
            writer.println("");
        }
    }

}
