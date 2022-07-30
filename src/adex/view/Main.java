/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.view;

import adex.bao.AssignmentBao;
import adex.bao.BaoFactory;
import adex.bao.DoctorBao;
import adex.bao.StudentBao;
import adex.bao.TABao;
import adex.dao.DoctorDaoImpl;
import adex.dto.AssignmentDto;
import adex.dto.CourseDto;
import adex.dto.DoctorDto;
import adex.dto.StudentDto;
import adex.dto.TADto;
import adex.dto.User;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Vega Laptop
 */
public class Main {
    Scanner input = new Scanner(System.in);
    BaoFactory fac = new BaoFactory();
    DoctorBao doctor = fac.getDoctorBao();
    StudentBao student = fac.getStudentBao();
    AssignmentBao assignment = fac.getAssignmentBao();
    TABao teacherAss = fac.getTABao();
    
    public void mainMenu(){
       System.out.print("\nplease make a choice:\n           1-Login\n           2-Sign up\n           3-Shutdown system\n           Enter your choice: ");
       int choice = input.nextInt();
       if(choice == 1){
           System.out.print("\nplease make a choice login in as a:\n           1-Doctor\n           2-Student\n           3-Teacher Assistant\n           Enter your choice: ");
           int check = input.nextInt();
           switch(check){
               case 1:
                   menuDoc(doctor.login());
                   break;
               case 2:
                   menuStudent(student.login());
                   break;
               case 3:
                   menuTA(teacherAss.login());
                   break;
           } 
       }else if(choice == 2){
           System.out.print("\nplease make a choice Sign Up as a:\n           1-Doctor\n           2-Student\n           3-Teacher Assistant\n           Enter your choice: ");
           int check = input.nextInt();
           switch(check){
                case 1:
                    menuDoc(doctor.signup());                    
                    break;
                case 2:
                    menuStudent(student.signup());
                    break;
                case 3:
                    menuTA(teacherAss.signup());
                    break;
            }      
       }else if(choice == 3){
           System.exit(0);
       }else{
           System.out.println("This num is not vaild");
           mainMenu();
       }
    }
      
    public void menuDoc(DoctorDto doc){      
        System.out.println("please make a choice:\n           1-List my courses\n           2-Creat a Public Course(public)\n           3-Creat a Private Course\n           4-Edit course\n           5-Log Out");
        int choice = input.nextInt();
        if(choice == 1){
            doctor.listMyCourses(doc);
            System.out.println("Enter  the course code you want to view:");
            String code = input.next();
            if(doc.getCourses().containsKey(code)){
                viewCourse(doc.getCourses().get(code), doc);
            }else{
                System.out.println("You don't have this course");
            } 
            menuDoc(doc);
        }else if(choice == 2){    
            doctor.createCourse(doc);
            menuDoc(doc);
        }else if(choice == 3){
            doctor.createPrivCourse(doc);
            menuDoc(doc);
        }else if(choice == 4){
                doctor.editCourse(doc);
                menuDoc(doc);
        }else if(choice == 5){
                mainMenu();
        }else{
            System.out.println("This num is not vaild");
            menuDoc(doc);
        }                
    }
     
    public  void viewCourse(CourseDto co,User u){      
        if(u instanceof DoctorDto){
            u = (DoctorDto) u;
        }else if(u instanceof TADto){
            u = (TADto) u;
        }
        System.out.println("please make a choice:\n           1-List Assignments\n           2-Creat Assignment\n           3-View Assignment\n           4-Back");
        int choice = input.nextInt();
        if(choice == 1){
            assignment.listAss(co);
            viewCourse(co,u);
        }else if(choice == 2){
            assignment.createAss(co);
            viewCourse(co,u);
        }else if(choice == 3){
            System.out.println("Enter Asssignment code:");
            String code = input.next();
            viewAssignment(co.getAssignment().get(code),co,u);
            viewCourse(co,u);
        }else if(choice == 4){
            if(u instanceof DoctorDto){
                menuDoc((DoctorDto)u);
            }else if(u instanceof TADto){
                menuTA((TADto)u);
            }
            
        }else{
            System.out.println("Enter a valid number");
            viewCourse(co,u);
        }
    } 
    
    public void viewAssignment(AssignmentDto ass, CourseDto co, User u){
        if(u instanceof DoctorDto){
            u = (DoctorDto) u;
        }else if(u instanceof TADto){
            u = (TADto) u;
        }
        System.out.println( "please make a choice:\n           1-Show Info\n           2-Show Grads Report\n           3-List Solutions\n           4-View Solution\n           5-Back");
        int choice = input.nextInt();
        if(choice == 1){
            assignment.showInfo(ass);
            viewAssignment(ass,co,u);
        }else if(choice == 2){
            assignment.showGradesRep(ass);
            viewAssignment(ass,co,u);
        }else if(choice == 3){
            assignment.listSolutions(ass);
            viewAssignment(ass,co,u);
        }else if(choice == 4){
            System.out.println("Enter  the student name:");
            String name = input.next();
            viewSolution(name,ass,co,u);
        }else if(choice == 5){
            viewCourse(co,u);
        }else{
            System.out.println("Enter a valid number");
            viewAssignment(ass,co,u);
        }
    
    
    }
    
    public void viewSolution(String name, AssignmentDto ass, CourseDto co, User u){
        if(u instanceof DoctorDto){
            u = (DoctorDto) u;
        }else if(u instanceof TADto){
            u = (TADto) u;
        }
        System.out.println( "please make a choice:\n           1-Show Info\n           2-Set Grad\n           3-Set a Comment\n           4-Back");
        int choice = input.nextInt();
        if(choice == 1){
            assignment.showSolInfo(name, ass);
            viewSolution(name,ass,co,u);
        }else if(choice == 2){
            assignment.setGrade(name, ass);
            viewSolution(name,ass,co,u);
        }else if(choice == 3){
            assignment.setComment(name, ass);
            viewSolution(name,ass,co,u);
        }else if(choice == 4){
            viewAssignment(ass,co,u);
        }else{
            System.out.println("Enter a valid number");
            viewSolution(name,ass,co,u);
        }
    }
    
    public void menuStudent(StudentDto stu){
        System.out.println("please make a choice:\n           1-Register in course \n           2-Register in private course \n           3-List My Courses\n           4-View a Course\n           5-Grades Report\n           6-Log Out");
        int choice = input.nextInt();
        if(choice == 1){
            student.registerC(stu);
            menuStudent(stu);
        }else if(choice == 2){
            student.registerPrivC(stu);
            menuStudent(stu);
        }else if(choice == 3){
            student.listMycourses(stu);
            menuStudent(stu);
        }else if(choice == 4){
            student.viewCourse(stu);
            menuStudent(stu);
        }else if(choice == 5){
            student.gradReports(stu);
            menuStudent(stu);
        }else if(choice == 6){
            mainMenu();
        }else{
            System.out.println("Enter a valid number");
            menuStudent(stu);
        } 
    }
    
    public void menuTA(TADto ta){
        System.out.println("please make a choice:\n           1-List courses\n           2-View Course\n           3-Ask For be TA\n           4-List Invitations\n           5-Accept an Invtation\n           6-Log Out");
        int choice = input.nextInt();
        if(choice == 1){
            teacherAss.listmyCourses(ta);
            menuTA(ta);
        }else if(choice == 2){    
            System.out.println("Enter  the course code:");
            String code = input.next();
            if(teacherAss.createAss(ta, code)){
                viewCourse(ta.getCourses().get(code), ta);
                menuTA(ta);
            }else{
                viewCourseTA(ta.getCourses().get(code), ta);
                menuTA(ta);
            }
            
        }else if(choice == 3){
            teacherAss.askForTA(ta);
            menuTA(ta);
        }else if(choice == 4){
            teacherAss.listmyInv(ta);
            menuTA(ta);
        }else if(choice == 5){
            teacherAss.acceptInv(ta);
            menuTA(ta);
        }else if(choice == 6){
                mainMenu();
        }else{
            System.out.println("This num is not vaild");
            menuTA(ta);
        }                
    }
    
    public  void viewCourseTA(CourseDto co,TADto ta){   
        System.out.println("please make a choice:\n           1-List Assignments\n           2-View Assignment\n           3-Back");
        int choice = input.nextInt();
        if(choice == 1){
            assignment.listAss(co);
            viewCourseTA(co,ta);
        }else if(choice == 2){
            System.out.println("Enter Asssignment code:");
            String code = input.next();
            viewAssignment(co.getAssignment().get(code),co,ta);
            viewCourseTA(co,ta);
        }else if(choice == 3){
            menuTA(ta);
        }else{
            System.out.println("Enter a valid number");
            viewCourseTA(co,ta);
        }
    } 
    
     public static void main(String[] args) {
        
         
        DummyData t = new DummyData();
        t.start();
        Main m = new Main();
        System.out.println(t.isAlive());
        m.mainMenu(); 

//        //String c = "hjgjd  jknkfg kejgt ";
//        DoctorDaoImpl d = new DoctorDaoImpl();
//        DoctorDto doc = new DoctorDto("Ali","00995");
//         System.out.println(d.decryptedPass(d.encryptedPass("ttttt", doc, 3), 3));
    }
    
}
