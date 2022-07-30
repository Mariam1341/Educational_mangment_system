/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dao;

import adex.dto.AssignmentDto;
import adex.dto.CourseDto;
import adex.dto.StudentDto;

/**
 *
 * @author Vega Laptop
 */
public interface AssignmentDao {
    public void listAss(CourseDto course);
    public void createAss(CourseDto course, String code, String info, double totalGrade,int deadline,String checkGroup);
    public void showInfo(AssignmentDto ass);
    public void showGradesRep(AssignmentDto ass);
    public void listSolutions(AssignmentDto ass);
    public void showSolInfo(String name, AssignmentDto ass);
    public void setGrade(String name, AssignmentDto ass, double grade);
    public void setComment(String name, AssignmentDto ass, String comment);
    public void solve(StudentDto student, AssignmentDto ass, String sol);
}
