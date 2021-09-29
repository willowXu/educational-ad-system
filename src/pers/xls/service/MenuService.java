package pers.xls.service;

import pers.xls.bean.Menu;
import pers.xls.bean.Role;

import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-27 20:22
 * @Modified by :
 */
public interface MenuService {

    /**
     * 查询菜单列表
     */
    public List<Menu> getMenuList();

    /**
     * 总条数
     */
    public int total();

}
