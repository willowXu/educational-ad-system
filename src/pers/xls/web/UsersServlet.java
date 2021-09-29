package pers.xls.web;

import pers.xls.bean.Users;
import pers.xls.dao.impl.UsersDaoImpl;
import pers.xls.service.UsersService;
import pers.xls.service.impl.UsersServiceImpl;
import pers.xls.util.PageUtil;

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
 * @Date Created in 2021-09-27 17:50
 * @Modified by :
 */
@WebServlet(urlPatterns = "/power/user/users")
public class UsersServlet extends HttpServlet {

    private UsersService usersService = new UsersServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if("select".equals(method)){
            select(req,resp);
        }
    }

    /**
     * 查询数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数 分页参数 模糊查询参数
        String index = req.getParameter("pageIndex");
        int pageIndex = (index==null||index.length()==0)?1:Integer.parseInt(index);

        //2.调取service方法（1.查询数据列表，2.查询总条数）
        PageUtil pageUtil = new PageUtil();
        pageUtil.setPageIndex(pageIndex);
        //获取用户列表
        List<Users> usersList = usersService.getUsersList(pageIndex,pageUtil.getPageSize());
        System.out.println("usersList:"+usersList.size());
        pageUtil.setDataList(usersList);
        //获取总条数
        int total = usersService.total();
        pageUtil.setTotal(total);

        //3.跳转页面
        req.setAttribute("p1",pageUtil);
        req.getRequestDispatcher("list.jsp").forward(req,resp);
    }
}
