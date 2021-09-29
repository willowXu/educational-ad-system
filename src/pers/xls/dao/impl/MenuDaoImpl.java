package pers.xls.dao.impl;

import pers.xls.bean.Menu;
import pers.xls.dao.MenuDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-27 20:25
 * @Modified by :
 */
public class MenuDaoImpl extends DBUtils implements MenuDao {

    @Override
    public List<Menu> getMenuList() {
        List<Menu> menuList = null;
        try {
            String sql = "select * from menu";
            resultSet = query(sql,null);
            if(resultSet!=null){
                menuList = new ArrayList<>();
                while(resultSet.next()){
                    Menu menu = new Menu();
                    menu.setMenuId(resultSet.getInt("menuid"));
                    menu.setMenuName(resultSet.getString("menuname"));
                    menu.setUpMenuId(resultSet.getInt("upmenuid"));
                    menu.setState(resultSet.getInt("state"));
                    menu.setDesc(resultSet.getString("desc"));
                    menu.setUrl(resultSet.getString("url"));
                    menuList.add(menu);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return menuList;
    }

    @Override
    public int total() {
        count = 0;
        try {
            String sql = "select count(*) from menu";
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
