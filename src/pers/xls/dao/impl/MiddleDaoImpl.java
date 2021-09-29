package pers.xls.dao.impl;

import pers.xls.bean.Menu;
import pers.xls.dao.MiddleDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-28 15:09
 * @Modified by :
 */
public class MiddleDaoImpl extends DBUtils implements MiddleDao {


    @Override
    public int insertMiddle(int roleId, String[] ids) {
        int k = 0;
        try {
            String sql = "insert into middle values(null,?,?)";
            //批量新增
            pps = getPps(sql);
            for (String id : ids) {
                pps.setInt(1,roleId);
                pps.setString(2,id);
                pps.addBatch();
            }
            pps.executeBatch();//受影响的行数
            k = 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return k;
    }

    @Override
    public List<Menu> getMenusByRoleId(int roleId) {
        List<Menu> menuList = new ArrayList<>();
        try {
            String sql = "select * from middle where roleId=?";
            List params = new ArrayList();
            params.add(roleId);
            resultSet = query(sql,params);
            while(resultSet.next()){
                Menu menu = new Menu();
                menu.setMenuId(resultSet.getInt("menuid"));
                menuList.add(menu);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return menuList;
    }

    @Override
    public int deleteMiddleOfRole(int roleId) {
        count = 0;
        try {
            String sql = "delete from middle where roleid=?";
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
