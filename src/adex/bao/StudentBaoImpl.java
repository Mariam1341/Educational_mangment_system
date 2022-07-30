/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.bao;

import adex.dao.DaoFactory;
import adex.dao.DataBase;
import adex.dao.DataBaseImpl;
import adex.dao.StudentDao;
import adex.dto.CourseDto;
import adex.dto.StudentDto;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Vega Laptop
 */
public class StudentBaoImpl implements StudentBao{
    Scanner input = new Scanner(System.in);
    DaoFactory fac = new DaoFactory();
    StudentDao student = fac.getStudentDao();
    
    @Override
    public StudentDto signup(){
        System.out.println("  Enter Full Name: ");
        String fullName = input.nextLine();
        System.out.println("  Enter User Name: ");
        String userName = input.nextLine();
        while(!(DataBaseImpl.validUserName(userName))){
            System.out.println("this User Name is used for anther user try again....");
            System.out.println("  Enter User Name: ");
            userName = input.nextLine();
        }
        System.out.println("  Enter Email: ");
        String email = input.nextLine();
        while(!(DataBaseImpl.validEmail(email))){
            System.out.println("this email is not valid try again....");
            System.out.println("  Enter Email: ");
            email = input.nextLine();
        }
        System.out.println("  Enter password: ");
        String password = input.nextLine();
        while(!(DataBaseImpl.validPass(password))){
            System.out.println("this password is not valid try again....\npassword should have more than 6 character");
            System.out.println("  Enter password: ");
            password = input.nextLine();
        }

        System.out.println("  Enter ID: ");
        String ID = input.nextLine();
        if(student.search(ID)){
           return student.signup(userName, ID, password, fullName, email); 
       }else{
           System.out.println("You have already an account... LOG IN");
           return login();
       }
    }
    
    @Override
    public StudentDto login(){
        System.out.println("  Enter User Name: ");
        String userName = input.nextLine();
        System.out.println("  Enter password: ");
        String password = input.nextLine();
        if(student.login(userName, password) == null){
            signup();
        }           
        return student.login(userName, password);
    }
      
    @Override
    public void registerC(StudentDto stu){
        DataBaseImpl.listAllCourses();
        System.out.println("\nEnter the code of the course you want to register in:  ");
        String choice = input.next();
        boolean check = stu.getMyCourses().containsKey(choice);
        while(check){
            System.out.println("you already register in this course before");
            System.out.println("Enter the code of the course you want to register in:");
            choice = input.next();
            check = stu.getMyCourses().containsKey(choice);
        }
        if(student.checkLimits(choice)){
             Map<String, CourseDto> map = student.checkPreReq(stu, choice);
            if(map.isEmpty()){
                student.registerC(stu, choice);
                System.out.println("You will receive your acceptance within "+DataBase.courses.get(choice).getPeriodAcc()+" days");
            }else{
                System.out.println("Tis course has pre-requistes ,You should take these courses before:");
                Set<String> keys = map.keySet();
                for(String key: keys){
                    System.out.println("- Course: "+map.get(key)+" Code: "+ key);
                }
                System.out.println("Do you want to regester in one of these courses now: ( y / n )");
                String ans = input.next();
                if(ans.equals("Y") || ans.equals("y")){
                    registerC(stu);
                }
            }
        }else{
            System.out.println("This course can't accept students any more \n");
        }         
    }
    
    @Override
    public void registerPrivC(StudentDto stu){
        System.out.println("\nEnter the encrypted code of the course you want to register in:  ");
        String choice = input.next();
        student.registerPrivC(stu, choice);
    }
    
    @Override
    public void listMycourses(StudentDto stu){
        student.listMycourses(stu);
    }
    
    @Override
    public void viewCourse( StudentDto stu){        
        listMycourses(stu);
        System.out.println("Enter  the course code:");
        String code = input.next();
        CourseDto course = student.getCourse(stu, code);
        System.out.println("Course "+ course.getName()+" with Code "+ course.getCode()+" - taught by Doctor "+course.getDoctorName());
        Set<String> keys = course.getAssignment().keySet();
        System.out.println("Course has "+course.getAssignment().size()+" assignment");
        int num = 1;
        for(String key : keys){
            if(course.getAssignment().get(key).getStudentsSolutions().isEmpty()){
                System.out.print(" and no solutions upload till now");
            }else{
                System.out.print("Assignment "+num+" "+course.getAssignment().get(key).getStudentsSolutions().get(stu.getUserName()).isSubmitted());
                System.out.print(" - "+course.getAssignment().get(key).getStudentsSolutions().get(stu.getUserName()).getStudentGrade());
                System.out.print(" / "+course.getAssignment().get(key).getTotalGrade());
                num++;
            } 
        }
        System.out.println();
        if(!course.getAssignment().isEmpty()){
            menuCourse(stu,course);
        }
        
    }
    
    public void menuCourse(StudentDto stu,CourseDto course){
        System.out.println("please make a choice:\n           1-Submit assignment solution \n           2-Unredister from course\n           3-Back\n           Enter Choice: ");
        int choice = input.nextInt();
        if(choice == 1){
            submitAss(stu, course);
        }else if(choice == 2){
            student.unregisterC(stu, course);
        }else if(choice == 3){
            
        }else{
            System.out.println("This number is not valid....try again");
            menuCourse(stu,course);
        }
    
    }
      
    public void submitAss(StudentDto stu,CourseDto course) {
     System.out.println("Enter  the assignment code:");
        String code = input.next();
        System.out.println("Enter  the assignment solution:");
        String sol = input.next();
        student.submitAss(course, stu, code, sol);
    }
    
    @Override
    public void gradReports(StudentDto stu){
         student.gradReports(stu);
    }
}
