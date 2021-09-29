package pers.xls.dao;

import pers.xls.bean.Menu;

import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-27 20:25
 * @Modified by :
 */
public interface MenuDao {
    /**
     * 查询菜单列表
     */
    public List<Menu> getMenuList();
    /**
     * 总条数
     */
    public int total();
}
