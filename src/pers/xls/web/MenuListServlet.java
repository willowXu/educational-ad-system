package pers.xls.web;

import pers.xls.bean.Menu;
import pers.xls.bean.Role;
import pers.xls.service.MenuService;
import pers.xls.service.RoleService;
import pers.xls.service.impl.MenuServiceImpl;
import pers.xls.service.impl.RoleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xuliushen
 * @Description: 查询指定角色的菜单列表
 * 修改角色的思路：
 * 1.查询功能
 *  1.1 查询角色的菜单列表
 *  1.2 查询全部菜单列表
 * 2.修改功能
 *  修改servlet
 * @Date Created in 2021-09-28 16:08
 * @Modified by :
 */
@WebServlet(urlPatterns = "/power/role/getMenuList")
public class MenuListServlet extends HttpServlet {

    private RoleService roleService = new RoleServiceImpl();
    private MenuService menuService = new MenuServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数
        String method = req.getParameter("method");
        String sid = req.getParameter("roleId");
        int roleId = Integer.parseInt(sid);

        //2.调用service方法
        //2.1 查询指定角色信息
        Role role = roleService.getRoleById(roleId);
        //2.2 查询菜单列表
        List<Menu> menuList = menuService.getMenuList();
        //标记是否选中
        for (Menu menu : role.getMenuList()) {//选中的菜单
            int id1 = menu.getMenuId();
            for (Menu menu1 : menuList) {
                if(id1==menu1.getMenuId()){//第一级选中了
                    menu1.setSelected(1);
                    for (Menu menu2 : role.getMenuList()) {
                        int id2 = menu2.getMenuId();
                        for (Menu menu3 : menu1.getSecondMenuList()) {
                            if(id2==menu3.getMenuId()){//第二级选中了
                                menu3.setSelected(1);
                            }
                        }
                    }
                }
            }
        }
        //3.跳转页面
        req.setAttribute("role", role);
        req.setAttribute("menuList", menuList);
        if("edit".equals(method)) {
            req.getRequestDispatcher("edit.jsp").forward(req, resp);
        }else if("show".equals(method)){
            req.getRequestDispatcher("info.jsp").forward(req, resp);
        }
    }

}
