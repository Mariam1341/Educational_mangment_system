/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.bao;

import adex.dto.CourseDto;
import adex.dto.StudentDto;

/**
 *
 * @author Vega Laptop
 */
public interface StudentBao {
    public StudentDto signup();
    public StudentDto login();
    public void registerC(StudentDto stu);
    public void listMycourses(StudentDto stu);
    public void viewCourse( StudentDto stu);
    public void gradReports(StudentDto stu);
    public void registerPrivC(StudentDto stu);
}
