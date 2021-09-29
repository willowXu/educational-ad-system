package pers.xls.bean;

import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-24 17:23
 * @Modified by :
 */
public class Grade {
    private Integer gradeId;
    private String gradeName;
    private List<Student> studentList;//年级和学生一对多

    public Grade(){}

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
