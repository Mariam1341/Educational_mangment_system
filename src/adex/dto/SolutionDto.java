/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dto;

import java.util.Date;

/**
 *
 * @author Vega Laptop
 */
public class SolutionDto {
    private double StudentGrade = 0;
    private boolean submitted = false;
    private String Solution;
    private String comment;
    private String studentName;
    private long CreationTime;
    private Date date = null;
    private boolean checkLate;

     public SolutionDto(){}
    public SolutionDto(String Solution, String studentName) {
        this.Solution = Solution;
        this.studentName = studentName;
        this.CreationTime=date.getTime();
    }

   
    
    public double getStudentGrade() {
        return StudentGrade;
    }

    public void setStudentGrade(double StudentGrade) {
        this.StudentGrade = StudentGrade;
    }

    public String isSubmitted() {
        if(submitted == true){
            return "sumitted";
        }return "NOT sumitted";
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public String getSolution() {
        return Solution;
    }

    public void setSolution(String Solution) {
        this.Solution = Solution;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
// comparacive don't know with date
    public boolean isCheckLate() {
        return checkLate;
    }

    public void setCheckLate(boolean checkLate) {
        this.checkLate = checkLate;
    }

    public long getCreationTime() {
        return CreationTime;
    }
    
    
}
