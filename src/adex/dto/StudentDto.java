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
public class StudentDto extends User {
    private Map<String, CourseDto> myCourses = new HashMap();
    private Map<String, CourseDto> noMyCourses = new HashMap();
    
    
    public StudentDto(){}
    
    public StudentDto(String userName, String ID) {
        super(userName, ID);
    }
    public StudentDto(String userName, String ID, String password, String fullName, String email) {
        super(userName, ID, password, fullName, email);
    }

    public Map<String, CourseDto> getMyCourses() {
        return myCourses;
    }

    public Map<String, CourseDto> getNoMyCourses() {
        return noMyCourses;
    }

    
   
}  

