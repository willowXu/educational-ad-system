package pers.xls.dao.impl;

import pers.xls.bean.Student;
import pers.xls.dao.StudentDao;
import pers.xls.util.StudentEnum;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-24 17:39
 * @Modified by :
 */
public class StudentDaoImpl extends DBUtils implements StudentDao {
    @Override
    public List<Student> getStudents(String name,String stuno,int sex,int pageIndex,int pageSize) {
        List<Student> studentList = null;
        try {
            StringBuffer sqlBuf = new StringBuffer("select * from student where state!=4");
            List params = new ArrayList();
            if(name!=null&&name.length()>0){
                sqlBuf.append(" and stuname like ?");
                params.add("%"+name+"%");
            }
            if(stuno!=null&&stuno.length()>0){
                sqlBuf.append(" and stuno like ?");
                params.add("%"+stuno+"%");
            }
            if(sex!=-1){
                sqlBuf.append(" and sex=?");
                params.add(sex);
            }
            //分页
            sqlBuf.append(" limit ?,?");
            params.add((pageIndex-1)*pageSize);
            params.add(pageSize);
            resultSet = query(sqlBuf.toString(),params);
            if(resultSet!=null){
                studentList = new ArrayList<>();
                while(resultSet.next()){
                    Student student = new Student();
                    student.setStuId(resultSet.getInt("stuid"));
                    student.setStuName(resultSet.getString("stuname"));
                    student.setStuNo(resultSet.getString("stuno"));
                    student.setSex(resultSet.getInt("sex"));
                    student.setPhone(resultSet.getString("phone"));
                    student.setProfession(resultSet.getString("profession"));
                    student.setRegDate(resultSet.getDate("regdate"));
                    studentList.add(student);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeAll();
        }
        return studentList;
    }

    @Override
    public int total(String name,String stuno,int sex){
        int total = 0;
        try {
            StringBuffer sqlBuf = new StringBuffer("select count(*) from student where state!=4");
            List params = new ArrayList();
            if(name!=null&&name.length()>0){
                sqlBuf.append(" and stuname like ?");
                params.add("%"+name+"%");
            }
            if(stuno!=null&&stuno.length()>0){
                sqlBuf.append(" and stuno like ?");
                params.add("%"+stuno+"%");
            }
            if(sex!=-1){
                sqlBuf.append(" and sex=?");
                params.add(sex);
            }
            resultSet = query(sqlBuf.toString(),params);//返回的是一个数值
            while(resultSet.next()){
                total = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeAll();
        }
        return total;
    }

    @Override
    public int insertStudent(Student student){
        count = 0;
        try {
            String sql = "insert into student values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            List params = new ArrayList();
            params.add(student.getStuName());
            params.add(student.getStuNo());
            params.add(student.getSex());
            params.add(student.getPhone());
            params.add(student.getEmail());
            params.add(student.getRegistered());
            params.add(student.getAddress());
            params.add(student.getProfession());
            params.add(student.getIdNumber());
            params.add(student.getPolitics());
            params.add(new Date());
            params.add(StudentEnum.READING.type);
            params.add(student.getIntroduction());
            params.add(student.getGid());
            count = update(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return count;
    }

    @Override
    public Student findById(int id) {
        Student student = null;
        try {
            String sql = "select * from student where stuid=?";
            List params = new ArrayList();
            params.add(id);
            resultSet = query(sql,params);
            if(resultSet!=null){
                while(resultSet.next()){
                    student = new Student();
                    student.setStuId(id);
                    student.setStuName(resultSet.getString("stuname"));
                    student.setStuNo(resultSet.getString("stuno"));
                    student.setSex(resultSet.getInt("sex"));
                    student.setPhone(resultSet.getString("phone"));
                    student.setEmail(resultSet.getString("email"));
                    student.setRegistered(resultSet.getString("registered"));
                    student.setAddress(resultSet.getString("address"));
                    student.setProfession(resultSet.getString("profession"));
                    student.setIdNumber(resultSet.getString("idNumber"));
                    student.setPolitics(resultSet.getString("politics"));
                    student.setIntroduction(resultSet.getString("introduction"));
                    student.setGid(resultSet.getInt("gid"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return student;
    }

    @Override
    public boolean findByStuNo(String stuNo) {
        boolean result = false;
        try {
            String sql = "select * from student where stuno=?";
            List params = new ArrayList();
            params.add(stuNo);
            resultSet = query(sql,params);
            while (resultSet.next()){
                result = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        System.out.println("stuno"+stuNo+"result:"+result);
        return  result;
    }

    @Override
    public int updateStudent(Student student) {
        count = 0;
        try {
            StringBuffer sqlBuf = new StringBuffer("update student set stuName=?,stuNo=?,sex=?," +
                    "phone=?,email=?,registered=?,address=?,profession=?,idNumber=?,politics=?," +
                    "introduction=?,gid=? where stuid=?");
            List params = new ArrayList();
            params.add(student.getStuName());
            params.add(student.getStuNo());
            params.add(student.getSex());
            params.add(student.getPhone());
            params.add(student.getEmail());
            params.add(student.getRegistered());
            params.add(student.getAddress());
            params.add(student.getProfession());
            params.add(student.getIdNumber());
            params.add(student.getPolitics());
            params.add(student.getIntroduction());
            params.add(student.getGid());
            params.add(student.getStuId());
            count = update(sqlBuf.toString(),params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return count;
    }

    @Override
    public int deleteStudent(int stuId) {
        count = 0;
        try {
            String sql = "update student set state=? where stuid=?";
            List params = new ArrayList();
            params.add(StudentEnum.DELETED.type);//删除
            params.add(stuId);
            count = update(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return count;
    }


}
