/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.bao;

import adex.dao.DaoFactory;
import adex.dao.DataBase;
import adex.dao.DataBaseImpl;
import adex.dao.DoctorDao;
import adex.dto.CourseDto;
import adex.dto.DoctorDto;
import adex.dto.StudentDto;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Vega Laptop
 */
public class DoctorBaoImpl implements DoctorBao{
    Scanner input = new Scanner(System.in);
    DaoFactory fac = new DaoFactory();
    DoctorDao doctor = fac.getDoctorDao();
    
    @Override
    public DoctorDto signup(){
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
        if(doctor.search(ID)){
           return doctor.signup(userName, ID, password, fullName, email); 
       }else{
           System.out.println("You have already an account... LOG IN");
           return login();
       }
    }
    
    @Override
    public DoctorDto login(){
        System.out.println("  Enter User Name: ");
        String userName = input.nextLine();
        System.out.println("  Enter password: ");
        String password = input.nextLine();
        if(doctor.login(userName, password) == null){
            signup();
        }           
        return doctor.login(userName, password);
    }
    @Override
    public void createCourse(DoctorDto doc){
        System.out.println("Enter  the course name:");
        String name = input.next();
        System.out.println("Enter  the course code:");
        String code = input.next();
        System.out.println("Enter  the course Summary:");
        String CourseSummary = input.next();   
        System.out.println("Enter  the course max students:");
        int maxStudent = input.nextInt(); 
        System.out.println("Enter  the course period of acceptance:");
        int periodAcc = input.nextInt(); 
        System.out.println("Enter  the number of pre-requisites courses:");
        int num = input.nextInt(); 
        String coursesCode[] = new String[num];
        if(num == 0){
            doctor.createCourse(name, code, CourseSummary, doc,maxStudent,periodAcc,coursesCode);
        }else{
            DataBaseImpl.listAllCourses();
            System.out.println("Enter  the pre-requisites courses code:");
            for(int i = 0; i <num;i++ ){
                String courseCode = input.next();
                coursesCode[i] = courseCode;
            } 
            doctor.createCourse(name, code, CourseSummary, doc,maxStudent,periodAcc,coursesCode);
        }
        
    }
    @Override
    public void createPrivCourse(DoctorDto doc){
        System.out.println("Enter  the course name:");
        String name = input.next();
        System.out.println("Enter  the course code:");
        String code = input.next();
        System.out.println("Enter  the course Summary:");
        String CourseSummary = input.next();     
        String encode = doctor.createPrivCourse(name, code, CourseSummary, doc);
        System.out.println("Send this code for your students to register in the course: " + encode);
    }
    
    @Override
    public void listMyCourses(DoctorDto doc){
        doctor.listCourses(doc);
    }
    
      
    
    @Override
    public void editCourse(DoctorDto doc){
        listMyCourses(doc);
        System.out.println("Enter the course code:");
        String code = input.next();
        System.out.println("You wanna edit:\n           1-Course name\n           2-Course code\n           3-Course summary\n           4-Course max students\n           5-course period of acceptance\n           6-pre-requisites courses");
        int choice = input.nextInt();
        if(choice == 6){
            preRequisites(code);
        }else{
            doctor.editCourse(code, choice);
        }
    }
        
    public void preRequisites(String codeC){
        CourseDto course = DataBase.courses.get(codeC);
        System.out.println("please make a choice:\n           1-Add Course\n           2-Delete Course\n           3-Delete all\n           4-Delete all then Add");
        int choice = input.nextInt();
        switch(choice){
            case 1:
                DataBaseImpl.listAllCourses();
                System.out.println("Enter the course code:");
                String code = input.next();
                course.getPreRequistes().put(code, DataBase.courses.get(code));
                break;
            case 2:
                Set<String> keys = course.getPreRequistes().keySet();
                for(String key: keys){
                    System.out.println(key);
                }
                System.out.println("Enter the course code to delete:");
                String codeD = input.next();
                course.getPreRequistes().remove(codeD);
                break;
            case 3:
                course.getPreRequistes().clear();
                break;
            case 4:
                course.getPreRequistes().clear();
                System.out.println("Enter  the number of pre-requisites courses:");
                int num = input.nextInt(); 
                String coursesCode[] = new String[num];
                if(num == 0){
                    break;
                }else{
                    DataBaseImpl.listAllCourses();
                    System.out.println("Enter  the pre-requisites courses code:");
                    for(int i = 0; i <num;i++ ){
                        String courseCode = input.next();
                        course.getPreRequistes().put(courseCode, DataBaseImpl.courses.get(courseCode));
                    } 
                }
                break;
                
        }
        


        
    }
    
    public void inviteTA(DoctorDto doc){
        System.out.println("Enter  the teacher assistant name:");
        String name = input.next();
        System.out.println("Enter  the Course Code:");
        String code = input.next();
        System.out.println("Can he create assignments (Y / N): ");
        String enableAss = input.next();
        if(enableAss.equals("Y") || enableAss.equals("y")){
            doctor.inviteTA(name, code, doc,true);
        }else if (enableAss.equals("N") || enableAss.equals("n")){
            doctor.inviteTA(name, code, doc,false);
        }      
    }
    
    public void askedTAlist(DoctorDto doc){
    doctor.listaskTA(doc);  
    }
    public void enableTA(CourseDto course,DoctorDto doc){
        askedTAlist(doc);
        System.out.println("Enter  the teacher assistant name:");
        String name = input.next();
        System.out.println("Can he create assignments (Y / N): ");
        String enableAss = input.next();
        if(enableAss.equals("Y") || enableAss.equals("y")){
            doctor.enableTA(course, name, true);
        }else if (enableAss.equals("N") || enableAss.equals("n")){
            doctor.enableTA(course, name, false);
        }     
    }
}
