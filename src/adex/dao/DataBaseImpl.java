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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Vega Laptop
 */
public class DataBaseImpl implements DataBase{
    @Override
    public void registerInCourses(StudentDto stu, CourseDto course){
        stu.getMyCourses().put(course.getCode(), course);
        stu.getNoMyCourses().remove(course.getCode());
        course.getStudents().put(stu.getUserName(), stu);
//        System.out.println(course.getCode());
    }
    
    @Override
    public void registerInCourses(StudentDto stu, CourseDto... courses){
        for(int i = 0; i < courses.length ;i++){
            registerInCourses(stu,courses[i]);
        }  
    }
    
    @Override
    public void teach(DoctorDto doc, CourseDto course ){
        doc.getCourses().put(course.getCode(), course);
        DataBaseImpl.courses.put(course.getCode(), course);
    }
    
    @Override
    public void addStu( CourseDto course,StudentDto... stus){
        for(int i = 0; i<stus.length ;i++){
            course.getStudents().put(stus[i].getUserName(), stus[i]);
        }
    }
    
    
            
    public static boolean validEmail(String email){
        int valid1 = 0;
        ArrayList arr = new ArrayList();
        for(int i = 0; i < email.length(); i++){
            int num = (int)email.charAt(i);
            if(num >= 65 && num <= 122){
                valid1++;
            }if (num == 64){
                for(int j = i; j < email.length(); j++){                   
                    arr.add(email.charAt(j));
                }break;
            }
        }
        if(!(valid1 >= 6)){
           return false; 
        }else{
            
            if (arr.size() == 10){
                String valid2 = "@gmail.com";
                for(int j = 0; j < valid2.length(); j++){
                    if(!(arr.get(j).equals(valid2.charAt(j)))){
                        return false;
                    }
                }
            }else{
                return false;
            }
        }
          
        return true;
    }
    
    public static boolean validUserName(String userName){
        Set<String> keysS = DataBaseImpl.students.keySet();
        Set<String> keysD = DataBaseImpl.doctors.keySet();
        //Set<String> keysS = DataBaseImpl.students.keySet();
        for(String key : keysS){
            if(key.equals(userName)){
                return false;
            }
        }for(String key : keysD){
            if(key.equals(userName)){
                return false;
            }
        }
        return true;
    }
    
    public static boolean validPass(String pass){
        int valid1 = 0;
        ArrayList arr = new ArrayList();
        for(int i = 0; i < pass.length(); i++){
            int num = (int)pass.charAt(i);
            if(num > 65 && num < 122){
                valid1++;
            }
        }
        if((valid1 >= 6)){
           return true; 
        }else{
           return false;
        } 
    }
    
    public static void listAllCourses(){
        System.out.println("All Courses list:");
        Set<String> codes = DataBase.courses.keySet();
        int num = 1;
        for(String key : codes){
            System.out.println(num+") Course: "+DataBase.courses.get(key).getName()+" - Code: "+ key );
            num++;
        }
    }
}
