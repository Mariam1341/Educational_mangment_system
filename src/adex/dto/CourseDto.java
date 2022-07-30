/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vega Laptop
 */
public class CourseDto {
    private String name, code, DoctorName, DoctorId, CourseSummary;//, DoctorDao doctor  doctor.addCourse(this);
    private int maxStudent,periodAcc;
    private Map<String, StudentDto> students = new HashMap();
    private Map<String, AssignmentDto> assignments = new HashMap();
    private Map<String, TADto> TAs = new HashMap();
    private Map<String, CourseDto> preRequistes = new HashMap();
    

    public CourseDto(String name, String code, String CourseSummary, String DoctorName, int maxStudent,int periodAcc,Map<String,CourseDto> preRequistes) {
        this.name = name;
        this.code = code;
        this.CourseSummary = CourseSummary;
        this.DoctorName = DoctorName;
        this.maxStudent = maxStudent;
        this.periodAcc = periodAcc;
        this.preRequistes = preRequistes;
        
    }
    
    public CourseDto(String name, String code, String CourseSummary, String DoctorName){
        this.name = name;
        this.code = code;
        this.CourseSummary = CourseSummary;
        this.DoctorName = DoctorName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public void setDoctorId(String doctorId) {
        DoctorId = doctorId;
    }

    public String getDoctorId() {
        return this.DoctorId;
    }
    public String getCourseSummary() {
        return CourseSummary;
    }
   
    public void printBasicDetails() {

        System.out.println("Course Name: " + name + "\tCode" + code);

    }

    public Map<String, StudentDto> getStudents() {
        return students;
    }

    public Map<String, AssignmentDto> getAssignment() {
        return assignments;
    }

    public Map<String, TADto> getTAs() {
        return TAs;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public int getPeriodAcc() {
        return periodAcc;
    }

    public Map<String, AssignmentDto> getAssignments() {
        return assignments;
    }

    public Map<String, CourseDto> getPreRequistes() {
        return preRequistes;
    }

    public void setCourseSummary(String CourseSummary) {
        this.CourseSummary = CourseSummary;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }

    public void setPeriodAcc(int periodAcc) {
        this.periodAcc = periodAcc;
    }

    public void setPreRequistes(Map<String, CourseDto> preRequistes) {
        this.preRequistes = preRequistes;
    }

    
    
    
}
