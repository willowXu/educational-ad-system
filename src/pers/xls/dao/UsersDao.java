package pers.xls.dao;

import pers.xls.bean.Menu;
import pers.xls.bean.Users;

import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-24 17:38
 * @Modified by :
 */
public interface UsersDao {
    /**
     * 登陆方法
     */
    public Users login(String username,String password);
    /**
     * 查询数据列表
     */
    public List<Users> getUsersList(int pageIndex,int pageSize);

    /**
     * 查询总条数
     */
    public int total();

}
