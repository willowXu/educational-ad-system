package pers.xls.service.impl;

import pers.xls.bean.Student;
import pers.xls.dao.StudentDao;
import pers.xls.dao.impl.StudentDaoImpl;
import pers.xls.service.StudentService;

import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-24 17:39
 * @Modified by :
 */
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student> getStudents(String name,String stuno,int sex,int pageIndex,int pageSize) {
        return studentDao.getStudents(name,stuno,sex,pageIndex,pageSize);
    }

    @Override
    public int total(String name,String stuno,int sex){
        return  studentDao.total(name,stuno,sex);
    }

    @Override
    public int insertStudent(Student student){
        return studentDao.insertStudent(student);
    }

    @Override
    public Student findById(int id) {
        return studentDao.findById(id);
    }

    @Override
    public boolean findByStuNo(String stuNo) {
        return studentDao.findByStuNo(stuNo);
    }

    @Override
    public int updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    @Override
    public int deleteStudent(int stuId) {
        return studentDao.deleteStudent(stuId);
    }
}
