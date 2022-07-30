/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dao;

import adex.dto.CourseDto;
import adex.dto.StudentDto;
import java.util.Map;

/**
 *
 * @author Vega Laptop
 */
public interface StudentDao {
    public StudentDto signup(String userName, String ID, String password, String fullName, String email);
    public StudentDto login(String userName,String password);
    public void registerC(StudentDto student,String choice);
    public void listMycourses(StudentDto student);
    public void unregisterC(StudentDto student, CourseDto course);
    public void submitAss( CourseDto course, StudentDto student, String code,String sol);
    public void gradReports(StudentDto student);
    public boolean search(String ID);
    public void listallCourses(StudentDto student);
    public CourseDto getCourse(StudentDto student, String code);
    public void registerPrivC(StudentDto student,String choice);
    public Map<String, CourseDto> checkPreReq(StudentDto student,String choice);
    public boolean checkLimits(String choice);
}
