package pers.xls.dao;

import pers.xls.bean.Menu;

import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-28 15:07
 * @Modified by :
 */
public interface MiddleDao {
    /**
     * 新增middle
     */
    public int insertMiddle(int roleId,String[] ids);

    /**
     * 查询指定roleId对应的MenuList
     */
    public List<Menu> getMenusByRoleId(int roleId);

    /**
     * 删除菜单项
     */
    public int deleteMiddleOfRole(int roleId);

}
