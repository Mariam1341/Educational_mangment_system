/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dao;

import adex.dto.CourseDto;
import adex.dto.DoctorDto;
import adex.dto.StudentDto;
import adex.dto.TADto;
import adex.dto.User;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/**
 *
 * @author Vega Laptop
 */
public interface DataBase {
    public static Map<String, StudentDto> students = new HashMap();
    public static Map<String, DoctorDto> doctors = new HashMap();
    public static Map<String, CourseDto> courses = new HashMap();
    public static Map<String, CourseDto> Pricourses = new HashMap();
    public static Map<String, TADto> TAs = new HashMap();
    public static Map<String,User> emails = new HashMap();
    public void registerInCourses(StudentDto stu, CourseDto... course);
    public void registerInCourses(StudentDto stu, CourseDto course);
    public void teach(DoctorDto doc, CourseDto course );
    public void addStu( CourseDto course,StudentDto... stus);
}
