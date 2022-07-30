/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dao;

import adex.dto.CourseDto;
import adex.dto.DoctorDto;

/**
 *
 * @author Vega Laptop
 */
public interface DoctorDao {
    public DoctorDto signup(String userName, String ID, String password, String fullName, String email);
    public DoctorDto login(String userName,String password);
    public String createPrivCourse(String name, String code, String CourseSummary, DoctorDto doctor);
    public void createCourse(String name, String code, String CourseSummary, DoctorDto doctor, int maxStudent,int periodAcc, String... coursesCode);
    public void listCourses(DoctorDto doctor);
     public void inviteTA(String name, String code,DoctorDto doctor,boolean enableAss);
    public boolean search(String ID);
    public void enableTA(CourseDto course, String name,boolean enableAss);
    public void listaskTA(DoctorDto doctor);
    public void editCourse(String code,int choice);
}
