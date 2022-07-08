package com.projectcgpa.entities;

import java.io.Serializable;

public class Semester implements Serializable {

    private int registerId;
    private String semester;
    private long studentId;
    private String courseCode;
    private int gradeId;

    public Semester() {
    }

    public Semester(int registerId, String semester, long studentId, String courseCode, int gradeId) {
        this.registerId = registerId;
        this.semester = semester;
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.gradeId = gradeId;
    }

    public int getRegisterId() {
        return registerId;
    }

    public void setRegisterId(int registerId) {
        this.registerId = registerId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }
}
