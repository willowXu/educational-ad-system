package pers.xls.service;

import pers.xls.bean.Grade;

import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-24 17:38
 * @Modified by :
 */
public interface GradeService {
    /**
     * 查询年级列表
     */
    List<Grade> getList();
}
