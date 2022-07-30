/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.bao;

import adex.dao.AssignmentDao;
import adex.dao.DaoFactory;
import adex.dto.AssignmentDto;
import adex.dto.CourseDto;
import adex.dto.StudentDto;
import java.util.Scanner;

/**
 *
 * @author Vega Laptop
 */
public class AssignmentBaoImpl implements AssignmentBao{
   
    Scanner input = new Scanner(System.in);
    DaoFactory fac = new DaoFactory();
    AssignmentDao assignment = fac.getAssignmentDao();
    
    @Override
    public void listAss(CourseDto course){
        assignment.listAss(course);
    }
    
    @Override
    public void createAss(CourseDto course){ 
       System.out.println("Enter  the Asssignment code:");
       String code = input.next();
       System.out.println("Enter  the Asssignment info:");
       String info = input.next();
       System.out.println("Do you want Assignment be solved by groups( y / n):");
       String checkGroup = input.next();
       System.out.println("Enter  the Asssignment total grade:");
       double totalGrade = input.nextDouble(); 
       System.out.println("Enter  the Asssignment deadLine:");
       int deadLine = input.nextInt(); 
       assignment.createAss(course, code, info, totalGrade,deadLine,checkGroup);
    }
    
    @Override
    public void showInfo(AssignmentDto ass){
        assignment.showInfo(ass);
    }
    
    @Override
    public void showGradesRep(AssignmentDto ass){
        assignment.showGradesRep(ass);
    }
    
    @Override
    public void listSolutions(AssignmentDto ass){
        assignment.listSolutions(ass);
    }
    
    @Override
    public void showSolInfo(String name, AssignmentDto ass){
        assignment.showSolInfo(name, ass);
    }
    
    @Override
    public void setGrade(String name, AssignmentDto ass){
        System.out.println("Enter  the student grade:");
        double grade = input.nextDouble();
        assignment.setGrade(name, ass, grade);
    }
    
    @Override
    public void setComment(String name, AssignmentDto ass){
        System.out.println("Enter  the comment:");
        String comment = input.next();
        ass.getStudentsSolutions().get(name).setComment(comment);
    }
    
    
}
