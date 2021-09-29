package pers.xls.dao.impl;

import pers.xls.bean.Menu;
import pers.xls.bean.Role;
import pers.xls.dao.RoleDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-27 19:30
 * @Modified by :
 */
public class RoleDaoImpl extends DBUtils implements RoleDao {

    @Override
    public List<Role> getRoleList(int pageIndex,int pageSize) {
        List<Role> roleList = null;
        try {
            String sql = "select * from role limit ?,?";
            List params = new ArrayList();
            params.add((pageIndex-1)*pageSize);
            params.add(pageSize);
            resultSet = query(sql,params);
            if(resultSet!=null){
                roleList = new ArrayList<>();
                while(resultSet.next()){
                    Role role = new Role();
                    role.setRoleId(resultSet.getInt("roleid"));
                    role.setRoleName(resultSet.getString("rolename"));
                    role.setRoleState(resultSet.getInt("rolestate"));
                    roleList.add(role);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return roleList;
    }

    @Override
    public int total() {
        count = 0;
        try {
            String sql = "select count(*) from role";
            resultSet = query(sql,null);
            while(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return count;
    }

    @Override
    public int insertRole(Role role) {
        int key = 0;//产生的id
        try {
            String sql = "insert into role values(?,?,?)";
            List params = new ArrayList();
            params.add(null);
            params.add(role.getRoleName());
            params.add(role.getRoleState());
            count = update(sql,params);
            ResultSet generatedKeys = pps.getGeneratedKeys();
            while(generatedKeys.next()){
                key = generatedKeys.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return key;
    }

    @Override
    public int updateRole(Role role) {
        return 0;
    }

    @Override
    public Role getRoleById(int roleId) {
        Role role = null;
        List<Menu> menuList = new ArrayList<>();
        try {
            String sql = "select * from role r,menu m,middle mi where r.roleid=mi.roleid and m.menuid=mi.menuid" +
                    " and r.roleid=?";
            List params = new ArrayList();
            params.add(roleId);
            resultSet = query(sql,params);
            while(resultSet.next()){
                role = new Role();
                role.setRoleId(roleId);
                role.setRoleName(resultSet.getString("rolename"));
                role.setRoleState(resultSet.getInt("rolestate"));
                Menu menu = new Menu();
                menu.setMenuId(resultSet.getInt("menuid"));
                menu.setMenuName(resultSet.getString("menuname"));
                menu.setUpMenuId(resultSet.getInt("upmenuid"));
                menu.setState(resultSet.getInt("state"));
                menu.setDesc(resultSet.getString("desc"));
                menu.setUrl(resultSet.getString("url"));
                menuList.add(menu);
            }
            role.setMenuList(menuList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return role;
    }

    @Override
    public int deleteRole(int roleId) {
        count = 0;
        try {
            String sql = "delete from role where roleid=?";
            List params = new ArrayList();
            params.add(roleId);
            count = update(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return count;
    }
}
