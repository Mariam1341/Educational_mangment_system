 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.view;

import adex.dao.AssignmentDao;
import adex.dao.DaoFactory;
import adex.dao.DataBase;
import adex.dao.DataBaseImpl;
import adex.dao.StudentDao;
import adex.dto.CourseDto;
import adex.dto.DoctorDto;
import adex.dto.StudentDto;
import adex.dto.TADto;
import java.util.Set;

/**
 *
 * @author Vega Laptop
 *///super(userName, ID, password, fullName, email);
public class DummyData extends Thread {
    DaoFactory fac = new DaoFactory();
    DataBase d = fac.getData();
    AssignmentDao ass = fac.getAssignmentDao();
    StudentDao stu = fac.getStudentDao();
    @Override
    public void run() {
        
        CourseDto c111 = new CourseDto("Prog1","CS111","summary1","Samy");
        CourseDto c112 = new CourseDto("Prog2","CS112","summary2","Morad");
        CourseDto c123 = new CourseDto("Math1","CS123","summary3","Ashraf");
        CourseDto c333 = new CourseDto("Math2","CS333","summary4","Hani");
        CourseDto c136 = new CourseDto("Prog3","CS136","summary5","Sayed");
        CourseDto c240 = new CourseDto("Stat1","CS240","summary6","Hussien");
        CourseDto c350 = new CourseDto("Stat2","CS350","summary7","Morad");
        
        
        DoctorDto do1 = new DoctorDto("Ali","10102345");
        DoctorDto do2 = new DoctorDto("Mostafa","20102345");
        DoctorDto do3 = new DoctorDto("Hani","30102345");
        DoctorDto do4 = new DoctorDto("Mohamed","40102345");
        DoctorDto do5 = new DoctorDto("Ashraf","50102345");
        DoctorDto do6 = new DoctorDto("S006","60102345","S006","Samy Hussien","samyH006@gmail.com");
        DoctorDto do7 = new DoctorDto("M007","70102345","M007","Morad Ali","moradA007@gmail.com");
        DoctorDto do8 = new DoctorDto("Sayed","80102345");
        DoctorDto do9 = new DoctorDto("Hussien","90102345");
        
        
        StudentDto stu1 = new StudentDto("s001","00102345","s001","Hussien Samy","hs001@gmail.com");
        StudentDto stu2 = new StudentDto("AshrafSayed","00204690");
        StudentDto stu3 = new StudentDto("MostafaHussien","00307035");
        StudentDto stu4 = new StudentDto("AliMohamed","00409380");
        StudentDto stu5 = new StudentDto("HaniSayed","00501725");
        
        TADto t1 = new TADto("t001","t0102345","t001","Tamer Samy","ts001@gmail.com");
        TADto t2 = new TADto("Mostfa","t0202345");
        TADto t3 = new TADto("Sayer","t0302345");
        
        
        
        DataBaseImpl.doctors.put("Ali", do1);
        DataBaseImpl.doctors.put("Mostafa", do2);
        DataBaseImpl.doctors.put("Hani", do3);
        DataBaseImpl.doctors.put("Mohamed", do4);
        DataBaseImpl.doctors.put("Ashraf", do5);
        DataBaseImpl.doctors.put("S006", do6);
        DataBaseImpl.doctors.put("M007", do7);
        DataBaseImpl.doctors.put("Sayed", do8);
        DataBaseImpl.doctors.put("Hussien", do9);
        
        
        DataBaseImpl.students.put("s001", stu1);
        DataBaseImpl.students.put("AshrafSayed", stu2);
        DataBaseImpl.students.put("MostafaHussien", stu3);
        DataBaseImpl.students.put("AliMohamed", stu4);
        DataBaseImpl.students.put("HaniSayed", stu5);
        
        DataBaseImpl.TAs.put(t1.getUserName(), t1);
        DataBaseImpl.TAs.put(t2.getUserName(), t2);
        DataBaseImpl.TAs.put(t3.getUserName(), t3);
        
        DataBaseImpl.courses.put("CS111", c111);
        DataBaseImpl.courses.put("CS112", c112);
        DataBaseImpl.courses.put("CS123", c123);
        DataBaseImpl.courses.put("CS333", c333);
        DataBaseImpl.courses.put("CS136", c136);
        DataBaseImpl.courses.put("CS240", c240);
        DataBaseImpl.courses.put("CS350", c350);
        
        
        
        
        
        
        
        d.teach(do3, c333);
        d.teach(do5, c123);
        d.teach(do6, c111);
        d.teach(do7, c112);
        d.teach(do8, c350);
        d.teach(do8, c136);
        d.teach(do9, c240);
       
        d.registerInCourses(stu1, c111, c112, c333, c136, c240, c350);
        d.registerInCourses(stu2,c111,c112,c123,c136,c240,c350,c333);
        d.registerInCourses(stu3,c112,c123,c136,c333);
        d.registerInCourses(stu4,c111,c112,c123,c136,c350,c333);
        d.registerInCourses(stu5,c111,c112,c123,c136,c240,c333);
       
//        ass.createAss(c111, "1", "ass1",24);
//        ass.createAss(c111, "2", "ass2",46);
//        ass.createAss(c111, "3", "ass3",28);
//        
//        ass.createAss(c333, "1", "ass1",35);
//        ass.createAss(c333, "2", "ass2",52);
//        ass.createAss(c333, "3", "ass3",25);
//        ass.createAss(c333, "4", "ass4",48);
        
//        stu.submitAss(c111, stu1, "1", "sol1");
//        stu.submitAss(c111, stu2, "1", "sol2");
//        stu.submitAss(c111, stu1, "1", "sol1");
//        ass.solve(stu1, c111.getAssignment().get(1), "sol1");
//        ass.solve(stu2, c111.getAssignment().get(1), "sol2");
        //ass.solve(stu1, .getAssignment().get(1), "sol1");
        
        //d.addStu(c111,new StudentDto("",""));
      
        
        
        System.out.println("now");
  
                       
    }
    
    

}
