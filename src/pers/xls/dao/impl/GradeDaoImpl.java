package pers.xls.dao.impl;

import pers.xls.bean.Grade;
import pers.xls.dao.GradeDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-24 17:38
 * @Modified by :
 */
public class GradeDaoImpl extends DBUtils implements GradeDao {
    @Override
    public List<Grade> getList() {
        List<Grade> gradeList = null;
        try {
            String sql = "select * from grade";
            resultSet = query(sql,null);
            if(resultSet!=null) {
                gradeList = new ArrayList<>();
                while (resultSet.next()) {
                    Grade grade = new Grade();
                    grade.setGradeId(resultSet.getInt("gradeid"));
                    grade.setGradeName(resultSet.getString("gradename"));
                    gradeList.add(grade);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return gradeList;
    }
}
