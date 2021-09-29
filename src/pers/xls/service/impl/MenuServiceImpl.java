package pers.xls.service.impl;

import pers.xls.bean.Menu;
import pers.xls.dao.MenuDao;
import pers.xls.dao.impl.MenuDaoImpl;
import pers.xls.service.MenuService;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-27 20:23
 * @Modified by :
 */
public class MenuServiceImpl implements MenuService {

    private MenuDao menuDao = new MenuDaoImpl();

    @Override
    public List<Menu> getMenuList() {
        List<Menu> menuList = menuDao.getMenuList();
        List<Menu> menuList1 = new ArrayList<>();
        //拆分一二级
        for (Menu menu : menuList) {
            if(menu.getUpMenuId()==0){//一级
                List<Menu> secondMenuList = new ArrayList<>();
                for (Menu menu1 : menuList) {//二级
                    if(menu1.getUpMenuId()==menu.getMenuId()){
                        secondMenuList.add(menu1);
                    }
                }
                menu.setSecondMenuList(secondMenuList);
                menuList1.add(menu);
            }
        }
        return menuList1;
    }

    @Override
    public int total() {
        return menuDao.total();
    }
}
