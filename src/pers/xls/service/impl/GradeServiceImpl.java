package pers.xls.service.impl;

import pers.xls.bean.Grade;
import pers.xls.dao.GradeDao;
import pers.xls.dao.impl.GradeDaoImpl;
import pers.xls.service.GradeService;

import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-24 17:38
 * @Modified by :
 */
public class GradeServiceImpl implements GradeService {

    private GradeDao gradeDao = new GradeDaoImpl();
    @Override
    public List<Grade> getList() {
        return gradeDao.getList();
    }
}
