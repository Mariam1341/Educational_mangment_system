/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.bao;

import adex.dto.AssignmentDto;
import adex.dto.CourseDto;

/**
 *
 * @author Vega Laptop
 */
public interface AssignmentBao {
    public void listAss(CourseDto course);
    public void createAss(CourseDto course);
    public void showInfo(AssignmentDto ass);
    public void showGradesRep(AssignmentDto ass);
    public void listSolutions(AssignmentDto ass);
    public void showSolInfo(String name, AssignmentDto ass);
    public void setGrade(String name, AssignmentDto ass);
    public void setComment(String name, AssignmentDto ass);
}
