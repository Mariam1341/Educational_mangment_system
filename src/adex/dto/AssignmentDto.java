/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
/**
 *
 * @author Vega Laptop
 */
public class AssignmentDto {
    
    private String code;
    private String info;
    private int deadLine;
    private static double gradesReport;
    private double TotalGrade;
    private long CreationTime;
    private static long AssignmentTime;
    private Date date = null;
    private boolean group = false;
    private Map<String, Double> studentsGrades = new HashMap();
    private Map<String, StudentDto[]> studentsGroup = new TreeMap();
    private Map<String,SolutionDto> studentsSolutions = new HashMap();
   

    
    public AssignmentDto(String code, double TotalGrade, String info, int deadLine) {
        this.code = code;
        this.TotalGrade = TotalGrade;
        this.date = new Date();
        this.CreationTime = date.getTime()+deadLine;
        this.info = info;
        this.deadLine = deadLine;
        
    }


    public String getCode() {
        return this.code;
    }

    public double getTotalGrade() {
        return this.TotalGrade;
    }

    public static long getAssignmentTime() {
        return AssignmentTime;
    }

    public void setAssignmentTime(int day, int hour) {
        this.AssignmentTime = this.CreationTime + (hour * (60 * 60 * 1000)) + (day * (24 * 60 * 60 * 1000));
    }

    public String getInfo() {
        return info;
    }

    public Map<String, Double> getStudentsGrades() {
        return studentsGrades;
    }

    public Map<String, SolutionDto> getStudentsSolutions() {
        return studentsSolutions;
    }

    public int getDeadLine() {
        return deadLine;
    }

    public boolean isGroup() {
        return group;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }

   
}
