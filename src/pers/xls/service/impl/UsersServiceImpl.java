package pers.xls.service.impl;

import pers.xls.bean.Menu;
import pers.xls.bean.Role;
import pers.xls.bean.Users;
import pers.xls.dao.RoleDao;
import pers.xls.dao.UsersDao;
import pers.xls.dao.impl.RoleDaoImpl;
import pers.xls.dao.impl.UsersDaoImpl;
import pers.xls.service.UsersService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-24 17:39
 * @Modified by :
 */
public class UsersServiceImpl implements UsersService {

    private UsersDao usersDao = new UsersDaoImpl();
    private RoleDao roleDao = new RoleDaoImpl();

    @Override
    public Users login(String username, String password) {
        Users users = usersDao.login(username,password);
        if(users==null){
            return null;
        }
        //三表联查（根据角色Id查询角色菜单信息）
        int roleId = users.getRoleId();
        Role role  = roleDao.getRoleById(roleId);
        List<Menu> menuList = role.getMenuList();
        //建立二级关系
        List<Menu> newMenuList = new ArrayList<>();
        for (Menu menu1 : menuList) {
            if(menu1.getUpMenuId()==0){//第一级
                List<Menu> secondMenuList = new ArrayList<>();
                for (Menu menu2 : menuList) {
                    if(menu1.getMenuId()==menu2.getUpMenuId()){//第二级
                        secondMenuList.add(menu2);
                    }
                }
                menu1.setSecondMenuList(secondMenuList);
                newMenuList.add(menu1);
            }
        }
        for (Menu menu : newMenuList) {
            System.out.println("第一级:"+menu.getMenuName());
            for (Menu menu1 : menu.getSecondMenuList()) {
                System.out.println("第二级："+menu1.getMenuName());
            }
        }
        role.setMenuList(newMenuList);
        users.setRole(role);
        return users;
    }

    @Override
    public List<Users> getUsersList(int pageIndex,int pageSize) {
        return  usersDao.getUsersList(pageIndex,pageSize);
    }

    @Override
    public int total() {
        return usersDao.total();
    }

    @Override
    public List<Menu> getMenuList(int userId) {
        return null;
    }
}
