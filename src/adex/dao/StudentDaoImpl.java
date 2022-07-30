/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dao;


import adex.dto.CourseDto;
import adex.dto.DoctorDto;
import adex.dto.SolutionDto;
import adex.dto.StudentDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Vega Laptop
 */
public class StudentDaoImpl implements StudentDao{
    //private Map<String, StudentDto> students = new HashMap();
    Scanner input = new Scanner(System.in);

    
    
    @Override
    public StudentDto signup(String userName, String ID, String password, String fullName, String email) {  
        StudentDto s = new StudentDto(userName, ID, password, fullName, email);
        DataBase.emails.put(email, s);
        DataBaseImpl.students.put(userName, s);
        return s;
    }
    
    
    @Override
    public boolean search(String ID){
        Set<String> keysS = DataBaseImpl.students.keySet();
        for(String key : keysS){
            if(ID.equals(DataBaseImpl.students.get(key).getID())){
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public StudentDto login(String userName,String password) {    
        Set<String> keys = DataBaseImpl.students.keySet();
        for(String key : keys){
            if(key.equals(userName)){
                if(DataBaseImpl.students.get(key).getPassword().equals(password)){
                    System.out.println("Welcomme "+DataBaseImpl.students.get(key).getFullName()+". You are logged in");
                    return DataBaseImpl.students.get(key);
                }else{
                    System.out.println("You enter wrong password try again:  ");
                    if(password.equals(input.next())){
                        System.out.println("Welcomme "+DataBaseImpl.students.get(key).getFullName()+". You are logged in");
                        return DataBaseImpl.students.get(key);
                    }else{
                        System.out.println("You enter wrong password try to login with your ID./nEnter your ID:  ");
                        if(DataBaseImpl.students.get(key).getID().equals(input.next())){
                            System.out.println("Welcomme "+DataBaseImpl.students.get(key).getFullName()+". You are logged in");
                            return DataBaseImpl.students.get(key);
                        }
                    }                  
                }
            }
        }
        System.out.println("\n**Not found account, you have to sign up:\n");return null;
    }
    
    public void nomycourse(StudentDto student){
        Set<String> keys = DataBaseImpl.courses.keySet();
        Set<String> mycourses = student.getMyCourses().keySet();
        for(String key : keys){
            for(String course : mycourses){
                if(!(key.equals(course))){
                    student.getNoMyCourses().put(key, DataBaseImpl.courses.get(key));
                }
            }
        }
    }
    
    @Override
    public void listallCourses(StudentDto student){
        nomycourse(student);
        Set<String> keys = student.getNoMyCourses().keySet();
        System.out.println("All Courses list:");
        int num = 1;
        for(String key : keys){
            System.out.println(num+") Course: "+student.getNoMyCourses().get(key).getName()+" - Code: "+ key );
            num++;
        }
    }
//    
    @Override
    public void registerC(StudentDto student,String choice){
        student.getMyCourses().put(choice, DataBaseImpl.courses.get(choice));  
    }

    @Override
    public Map<String, CourseDto> checkPreReq(StudentDto student,String choice){
        Set<String> keys = DataBase.courses.get(choice).getPreRequistes().keySet();
        Map<String, CourseDto> map = new HashMap();
        for(String key: keys){
            if(!(student.getMyCourses().containsKey(key))){
                map.put(key, DataBase.courses.get(key));
            }
        }
        return map;        
    }
    
    
    @Override
    public boolean checkLimits(String choice){
        if(DataBase.courses.get(choice).getMaxStudent() == DataBase.courses.get(choice).getStudents().size()){
            return false;
        }else{
            return true;
        }
    }
    
    @Override
    public void registerPrivC(StudentDto student,String choice){
        student.getMyCourses().put(DataBaseImpl.Pricourses.get(decryptedPass(choice)).getCode(),DataBaseImpl.Pricourses.get(decryptedPass(choice)));
    }
    
    @Override
    public void listMycourses(StudentDto student){
        Set<String> keys = student.getMyCourses().keySet();
        System.out.println("My Courses list:");
        int num = 0;
        for(String key : keys){
            num++;
            System.out.println(num+") Course: "+student.getMyCourses().get(key).getName()+" - Code: "+ key );
        }
        System.out.println("which ith [1 - "+num+"] course to view?");
       
    }   
    
    @Override
    public CourseDto getCourse(StudentDto student, String code){
        return student.getMyCourses().get(code);
    }
   
    @Override
    public void unregisterC(StudentDto student, CourseDto course){
        student.getNoMyCourses().put(course.getCode(), course);
        student.getMyCourses().remove(course.getCode());
        course.getStudents().remove(student.getID());
    }
    
    @Override
    public void submitAss( CourseDto course, StudentDto student, String code,String sol){   
//        course.getAssignment().get(code).getStudentsSolutions().get(student.getUserName()).setSolution(sol);
//        course.getAssignment().get(code).getStudentsSolutions().get(student.getUserName()).setSubmitted(true);        
        SolutionDto solu = new SolutionDto(student.getUserName(),sol);
        course.getAssignment().get(code).getStudentsSolutions().put(student.getUserName(), solu);
        if(course.getAssignment().get(code).getDeadLine()<solu.getCreationTime()){
            solu.setCheckLate(true);
        }
    }
    
    public void submitAssG( CourseDto course, StudentDto student, String code,String sol){
    
    }
    
    @Override
    public void gradReports(StudentDto student){
        Set<String> keys = student.getMyCourses().keySet();
        for(String key : keys){
            double totalGrade = 0;
            double total = 0;
            int totalasssubm = 0;
            System.out.print("Course code "+ key);
            Set<String> assignments = student.getMyCourses().get(key).getAssignment().keySet();
            for(String ass : assignments){
                if(student.getMyCourses().get(key).getAssignment().get(ass).getStudentsSolutions().get(student.getUserName()).isSubmitted().equals("sumitted")){
                    totalasssubm ++;
                }
                totalGrade +=student.getMyCourses().get(key).getAssignment().get(ass).getStudentsSolutions().get(student.getUserName()).getStudentGrade();
                total += student.getMyCourses().get(key).getAssignment().get(ass).getTotalGrade(); 
            }
            System.out.print(" - Total submitted "+totalasssubm+" assignments");
            System.out.println(" - Grade "+totalGrade+" / "+total); 
            System.out.println();
        }
    }
    
    public String decryptedPass(String encryptedPass){
        String demessage = "";
        for(int i = 0; i < encryptedPass.length();i++){  
            char ch =  encryptedPass.toCharArray()[i];
            if(ch >= 'a' && ch <= 'z'){
                ch -= 3; 
                if (ch > 'z'){
                    ch += (-'z' + 'a' -1);
                } 
            }else if(ch >= 'A' && ch <= 'Z'){
                ch -= 3;
                if (ch > 'Z'){
                    ch += (-'Z' + 'A' -1);
                }
            }
                demessage += ch;
        }
        return demessage;
    }
}
