package pers.xls.service;


import pers.xls.bean.Student;

import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-24 17:38
 * @Modified by :
 */
public interface StudentService {

    /**
     * 获取学生所有信息
     */
    public List<Student> getStudents(String name,String stuno,int sex,int pageIndex,int pageSize);
    /**
     * 获得总条数
     */
    public int total(String name,String stuno,int sex);

    /**
     * 添加学生
     */
    public int insertStudent(Student student);

    /**
     * 通过id查询学生
     */
    public Student findById(int id);

    /**
     * 通过stuNo查询学生
     */
    public boolean findByStuNo(String stuNo);

    /**
     * 修改学生
     */
    public int updateStudent(Student student);

    /**
     * 删除学生
     */
    public int deleteStudent(int  stuId);


}
