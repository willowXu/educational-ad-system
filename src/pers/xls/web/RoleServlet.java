package pers.xls.web;

import pers.xls.bean.Menu;
import pers.xls.bean.Role;
import pers.xls.service.MenuService;
import pers.xls.service.RoleService;
import pers.xls.service.impl.MenuServiceImpl;
import pers.xls.service.impl.RoleServiceImpl;
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
 * @Date Created in 2021-09-27 19:28
 * @Modified by :
 */

@WebServlet(urlPatterns = "/power/role/roles")
public class RoleServlet extends HttpServlet {

    private RoleService roleService =  new RoleServiceImpl();
    private MenuService menuService = new MenuServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if("select".equals(method)){
            select(req,resp);
        }else if("selectMenus".equals(method)){
            selectMenus(req,resp);
        }else if("add".equals(method)){
            add(req,resp);
        }else if("update".equals(method)){
            update(req,resp);
        }else if("delete".equals(method)){
            delete(req,resp);
        }
    }

    /**
     * 查询角色列表
     */
    public void select (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数
        String index = req.getParameter("pageIndex");
        int pageIndex = (index==null||index.length()==0)?1:Integer.parseInt(index);

        //2.调用service方法
        PageUtil pageUtil = new PageUtil();
        pageUtil.setPageIndex(pageIndex);
        //列表
        List<Role> roleList =  roleService.getRoleList(pageIndex,pageUtil.getPageSize());
        pageUtil.setDataList(roleList);
        //总条数
        int total = roleService.total();
        pageUtil.setTotal(total);

        //3.跳转页面
         req.setAttribute("p1",pageUtil);
         req.getRequestDispatcher("list.jsp").forward(req,resp);
    }

    /**
     * 查询菜单列表
     */
    public void selectMenus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //2.调用service方法
        //列表
        List<Menu> menuList =  menuService.getMenuList();

        //3.跳转页面
        req.setAttribute("menuList",menuList);
        req.getRequestDispatcher("add.jsp").forward(req,resp);
    }

    /**
     * 新增角色
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数
        String roleName = req.getParameter("roleName");
        String state = req.getParameter("state");
        String[] menuIds = req.getParameterValues("menu");
        //2.调用service方法
        int result = roleService.insertRole(roleName,state,menuIds);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(result>0){
            writer.println("<script>alert('新增角色成功！');location.href='/power/role/roles?method=select';</script>");
        }else{
            writer.println("<script>alert('新增角色失败！');location.href='/power/role/roles?method=selectMenus';</script>");
        }
    }

    /**
     * 修改角色
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数
        String roleId =req.getParameter("roleId");
        String roleName =  req.getParameter("roleName");
        String state = req.getParameter("state");
        String[] menuIds = req.getParameterValues("menu");
        //2.调用service方法:修改角色表
        int result = roleService.updateRole(Integer.parseInt(roleId),roleName,state,menuIds);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(result>0){
            writer.println("<script>alert('修改成功！'); location.href='/power/role/roles?method=select'; </script> ");
        }else{
            writer.println("<script>alert('修改失败！'); location.href='/power/role/getMenuList'; </script> ");
        }

    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数
        String roleId = req.getParameter("roleId");
        //2.调用service方法
        int result = roleService.deleteRole(Integer.parseInt(roleId));
        //3.跳转页面
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(result>0){
            writer.println("<script>alert('删除成功！'); location.href='/power/role/roles?method=select'; </script>");
        }else{
            writer.println("<script>alert('删除失败！'); location.href='/power/role/roles?method=select'; </script>");
        }
    }

}
