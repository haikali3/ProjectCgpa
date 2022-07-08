package com.projectcgpa.entities;

import java.io.Serializable;

public class Grade implements Serializable {
    private int gradeId;
    private String grade;
    private double points;

    public Grade() {
    }

    public Grade(int gradeId, String grade, double points) {
        this.gradeId = gradeId;
        this.grade = grade;
        this.points = points;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public double getPoints() {
        if(this.grade.equalsIgnoreCase("A+")){
            points = 4.00;
        }
        else if (this.grade.equalsIgnoreCase("A")){
            points = 4.00;
        }
        else if (this.grade.equalsIgnoreCase("A-")){
            points = 3.67;
        }
        else if (this.grade.equalsIgnoreCase("B+")){
            points = 3.33;
        }
        else if (this.grade.equalsIgnoreCase("B")){
            points = 3.00;
        }
        else if (this.grade.equalsIgnoreCase("B-")){
            points = 2.67;
        }
        else if (this.grade.equalsIgnoreCase("C+")){
            points = 2.33;
        }
        else if (this.grade.equalsIgnoreCase("C")){
            points = 2.00;
        }
        else if (this.grade.equalsIgnoreCase("C-")){
            points = 1.67;
        }
        else if (this.grade.equalsIgnoreCase("D+")){
            points = 1.33;
        }
        else if (this.grade.equalsIgnoreCase("D")){
            points = 1.00;
        }
        else if (this.grade.equalsIgnoreCase("E")){
            points = 0.67;
        }
        else
            points = 0.00;

        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }


}
