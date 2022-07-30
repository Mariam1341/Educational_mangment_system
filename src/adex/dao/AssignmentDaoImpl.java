/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dao;

import adex.dto.AssignmentDto;
import adex.dto.CourseDto;
import adex.dto.SolutionDto;
//import adex.dto.SolutionDto;
import adex.dto.StudentDto;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Vega Laptop
 */
public class AssignmentDaoImpl implements AssignmentDao{
    Scanner input = new Scanner(System.in);
    
    @Override
    public void listAss(CourseDto course){
        Set<String> keys = course.getAssignment().keySet();
        for(String key : keys){
            System.out.print("Code: "+course.getAssignment().get(key).getCode() );
            System.out.print("Info: "+course.getAssignment().get(key).getInfo());
            System.out.println("Deadline: "+course.getAssignment().get(key).getDeadLine());
        }
    }
    
    @Override
    public void createAss(CourseDto course, String code, String info, double totalGrade, int deadline,String checkGroup){
        AssignmentDto ass = new AssignmentDto(code,totalGrade,info,deadline);
        if(checkGroup.equals("Y")||checkGroup.equals("y")){
            ass.setGroup(true);
        }
        course.getAssignment().put(code, ass);
        Set<String> keys = course.getStudents().keySet();
        for(String key: keys){
            course.getAssignment().get(code).getStudentsSolutions().put(key, new SolutionDto());          
        }
    }
    
    @Override
    public void showInfo(AssignmentDto ass){
        System.out.println(ass.getInfo());
    }
    
    @Override
    public void showGradesRep(AssignmentDto ass){
        Set<String> keys = ass.getStudentsGrades().keySet();
        for(String key : keys){
            System.out.println("Student name: "+key+"Student grade: "+ass.getStudentsGrades().get(key));
        }
    }
    
    @Override
    public void listSolutions(AssignmentDto ass){
        Set<String> keys = ass.getStudentsSolutions().keySet();
        for(String key : keys){
            System.out.println("Student name: "+key+"Student state solution: "+ass.getStudentsSolutions().get(key).isSubmitted()+"  "+ass.getStudentsSolutions().get(key).isCheckLate());
        }
    }
    
    public void showSolInfo(String name, AssignmentDto ass){        
        System.out.println(ass.getStudentsSolutions().get(name).getSolution());      
    }
    
    @Override
    public void setGrade(String name, AssignmentDto ass, double grade){
        ass.getStudentsGrades().put(name, grade);
    }
    
    @Override
    public void setComment(String name, AssignmentDto ass, String comment){
        ass.getStudentsSolutions().get(name).setComment(comment);
    }
    
    @Override
    public void solve(StudentDto student, AssignmentDto ass, String sol){    
        ass.getStudentsSolutions().get(student.getUserName()).setSolution(sol);
    }
    
//    public void solve(StudentDto student, CourseDto course){
//   if () {
//       System.out.println(" Enter your solution...");
//       AssignmentDto.setSolution(input.nextLine()) ;
////            AssignmentDto.isSubmitted() = true;
//   } else {
//
//       System.out.println("you already has a solution. \n Need to update your solution? type y for \"Yes\" or n for \"No\"");
//       char ch = input.next().charAt(0);
//       while (ch != 'y' && ch != 'n') {
//           System.out.println("Wrong input .. Try Again.");
//           ch = input.next().charAt(0);
//       }
//
//       if (ch == 'y') {
//           System.out.println(" Enter your new solution...");
//       AssignmentDto.setSolution(input.nextLine()) ;
////            AssignmentDto.isSubmitted() = true;
//       }
//   }
//    }
//    }
    
    
}