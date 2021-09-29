package pers.xls.service.impl;

import pers.xls.bean.Menu;
import pers.xls.bean.Role;
import pers.xls.dao.MenuDao;
import pers.xls.dao.MiddleDao;
import pers.xls.dao.RoleDao;
import pers.xls.dao.impl.MenuDaoImpl;
import pers.xls.dao.impl.MiddleDaoImpl;
import pers.xls.dao.impl.RoleDaoImpl;
import pers.xls.service.RoleService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-27 19:31
 * @Modified by :
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao = new RoleDaoImpl();
    private MiddleDao middleDao = new MiddleDaoImpl();

    @Override
    public List<Role> getRoleList(int pageIndex,int pageSize) {
        return roleDao.getRoleList(pageIndex,pageSize);
    }

    @Override
    public int total() {
        return roleDao.total();
    }

    @Override//事务问题
    public int insertRole(String roleName,String state,String[] ids) {
        int result = 0;
        try {
            //1.新增角色
            Role role =  new Role();
            role.setRoleName(roleName);
            role.setRoleState(Integer.parseInt(state));
            int key = roleDao.insertRole(role);//产生的id值
            //2.新增middle
            middleDao.insertMiddle(key,ids);
            result = 1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateRole(int roleId, String roleName, String roleState, String[] ids) {
        int result = 0;
        try {
            //1.更新角色
            Role role = new Role();
            role.setRoleId(roleId);
            role.setRoleName(roleName);
            role.setRoleState(Integer.parseInt(roleState));
            roleDao.updateRole(role);
            //2.更新middle表
            //2.1删除原来的菜单项目
            middleDao.deleteMiddleOfRole(roleId);
            //2.2插入新的菜单项目
            middleDao.insertMiddle(roleId,ids);
            result = 1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Role getRoleById(int roleId) {
        //1.查询role数据表
        Role role =  roleDao.getRoleById(roleId);
        //2.查询middle数据表
        List<Menu> menus = middleDao.getMenusByRoleId(roleId);
        role.setMenuList(menus);
        return role;
    }

    @Override
    public int deleteRole(int roleId) {
        int result = 0;
        try {
            //删除与角色关联的middle(删除该角色与菜单之间的关系)
            middleDao.deleteMiddleOfRole(roleId);
            //删除角色
            roleDao.deleteRole(roleId);
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
