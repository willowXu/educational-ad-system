package pers.xls.dao;

import pers.xls.bean.Role;

import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-27 19:30
 * @Modified by :
 */
public interface RoleDao {
    /**
     * 查询角色列表
     */
    public List<Role> getRoleList(int pageIndex,int pageSize);

    /**
     * 总条数
     */
    public int total();

    /**
     * 新增角色
     */
    public int insertRole(Role role);

    /**
     * 修改角色
     */
    public int updateRole(Role role);

    /**
     * 根据id查询角色
     */
    public Role getRoleById(int roleId);

    /**
     * 删除角色
     */
    public int deleteRole(int roleId);

}
