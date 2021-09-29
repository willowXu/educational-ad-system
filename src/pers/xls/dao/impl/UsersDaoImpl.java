package pers.xls.dao.impl;

import pers.xls.bean.Menu;
import pers.xls.bean.Role;
import pers.xls.bean.Users;
import pers.xls.dao.UsersDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-24 17:39
 * @Modified by :
 */
public class UsersDaoImpl extends DBUtils implements UsersDao {

    @Override
    public Users login(String username, String password) {
        Users users  = null;
        try {
            String sql = "select * from users where loginname=? and password=?";
            List params = new ArrayList();
            params.add(username);
            params.add(password);
            resultSet = query(sql,params);
            if(resultSet!=null) {
                while (resultSet.next()) {
                    users = new Users();
                    users.setUserId(resultSet.getInt("userId"));
                    users.setLoginName(username);
                    users.setPassword(password);
                    users.setRealName(resultSet.getString("realName"));
                    users.setRoleId(resultSet.getInt("roleid"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeAll();
        }
        return users;
    }

    @Override
    public List<Users> getUsersList(int pageIndex,int pageSize) {
        List<Users> usersList = null;
        try {
            String sql = "select userid,loginname,realname,rolename from users u,role r where u.roleid=r.roleid limit ?,?";
            List params = new ArrayList();
            params.add((pageIndex-1)*pageSize);
            params.add(pageSize);
            resultSet = query(sql,params);
            if(resultSet!=null) {
                usersList = new ArrayList<>();
                while (resultSet.next()) {
                    Users users = new Users();
                    users.setUserId(resultSet.getInt("userid"));
                    users.setLoginName(resultSet.getString("loginname"));
                    users.setRealName(resultSet.getString("realname"));
                    Role role = new Role();
                    role.setRoleName(resultSet.getString("rolename"));
                    //建立关系
                    users.setRole(role);
                    usersList.add(users);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return usersList;
    }

    @Override
    public int total() {
        count = 0;
        try {
            String sql = "select count(*) from users u,role r where u.roleid=r.roleid";
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

}
