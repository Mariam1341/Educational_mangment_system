/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dao;

import adex.dto.CourseDto;
import adex.dto.DoctorDto;
import adex.dto.AskFormDto;
import java.util.Scanner;
import java.util.*;
//should make accept students request for courses (i can't understant acceptance system )
/**
 *
 * @author Vega Laptop
 */
public class DoctorDaoImpl implements DoctorDao {

    Scanner input = new Scanner(System.in);
    //private Map<String, CourseDto> courses = new HashMap();

    @Override
    public DoctorDto signup(String userName, String ID, String password, String fullName, String email) {
        DoctorDto d = new DoctorDto(userName, ID, password, fullName, email);
        DataBase.emails.put(email, d);
        DataBase.doctors.put(userName, d);
        return d;
    }

    @Override
    public DoctorDto login(String userName, String password) {
        Set<String> keys = DataBaseImpl.doctors.keySet();
        for (String key : keys) {
            if (key.equals(userName)) {
                if (DataBaseImpl.doctors.get(key).getPassword().equals(password)) {
                    return DataBaseImpl.doctors.get(key);
                } else {
                    System.out.println("You enter wrong password try again:  ");
                    if (password.equals(input.next())) {
                        return DataBaseImpl.doctors.get(key);
                    } else {
                        System.out.println("You enter wrong password try to login with your ID./nEnter your ID:  ");
                        if (DataBaseImpl.doctors.get(key).getID().equals(input.next())) {
                            return DataBaseImpl.doctors.get(key);
                        }
                    }
                }
            }
        }
        System.out.println("\n**Not found account, you have to sign up:\n");
        return null;
    }

    @Override
    public void createCourse(String name, String code, String CourseSummary, DoctorDto doctor, int maxStudent,int periodAcc, String[] coursesCode) {
        Map<String,CourseDto> arr = new HashMap();
        for(int i = 0; i < coursesCode.length;i++){
            DataBase.courses.get(coursesCode[i]).getCode();
            arr.put(coursesCode[i], DataBase.courses.get(coursesCode[i]));
        }
        CourseDto course = new CourseDto(name, code, CourseSummary, doctor.getUserName(),maxStudent,periodAcc,arr);
        doctor.getCourses().put(code, course);
        DataBaseImpl.courses.put(code, course);

    }

    @Override
    public void listCourses(DoctorDto doctor) {
        Set<String> keys = doctor.getCourses().keySet();
        for (String key : keys) {
            System.out.println("Code: " + key + " Course: " + doctor.getCourses().get(key).getName());
        }
    }
    
    public void listallTAs() {
        Set<String> keys = DataBase.TAs.keySet();
        int num = 0;
        System.out.println("Teacher Assistants list:");
        for (String key : keys) {
            System.out.print(num + ") Name: " + DataBase.TAs.get(key).getUserName());
            Set<String> cs = DataBase.TAs.get(key).getCourses().keySet();
            for (String c : cs) {
                System.out.print("His courses: " + DataBase.TAs.get(key).getCourses().get(c).getCode());
            }
            System.out.println();
        }
    }
    
    @Override
    public void inviteTA(String name, String code, DoctorDto doctor,boolean enableAss) {
        AskFormDto inv = new AskFormDto(DataBase.courses.get(code), doctor.getUserName(), name,enableAss);
        DataBase.TAs.get(name).getInvitationsC().put(doctor.getUserName(), inv);
    }
    
    @Override
    public void listaskTA(DoctorDto doctor){ 
        Set<String> keys = doctor.getAskTAs().keySet();
        int num = 0;
        System.out.println("Teacher Assistants list:");
        for (String key : keys) {
            System.out.println(num + ") Name: " + doctor.getAskTAs().get(key)+" asked to be a teacher assistant of your course"+doctor.getAskTAs().get(key).getCourse().getCode());
        }
    }
    
    @Override
    public void enableTA(CourseDto course, String name,boolean enableAss ) {    
        DataBase.TAs.get(name).getInvitationsC().get(course.getDoctorName()).setIsAccept(true);
        if(enableAss){
            DataBase.TAs.get(name).getCourseAss().put(course.getCode(), course);
        }else{
            DataBase.TAs.get(name).getCourses().put(course.getCode(), course);
        }
        
    }
    
    @Override
    public boolean search(String ID) {
        Set<String> keysS = DataBaseImpl.doctors.keySet();
        for (String key : keysS) {
            if (ID.equals(DataBaseImpl.doctors.get(key).getID())) {
                return false;
            }
        }

        return true;
    }
   
    @Override
    public String createPrivCourse(String name, String code, String CourseSummary, DoctorDto doctor) {
        String encode = encryptedPass(code, doctor);
        CourseDto course = new CourseDto(name, code, CourseSummary, doctor.getUserName());
        doctor.getPcourses().put(code, course);
        DataBaseImpl.courses.put(encode, course);
        return encode;
    }
    
    public String encryptedPass(String code, DoctorDto doc) {
        String message = doc.getUserName() + code;
        String enmessage = "";
        for (int i = 0; i < message.length(); i++) {
            char ch = message.toCharArray()[i];
            if (ch >= 'a' && ch <= 'z') {
                ch += 3;
                if (ch > 'z') {
                    ch += (-'z' + 'a' - 1);
                }
            } else if (ch >= 'A' && ch <= 'Z') {
                ch += 3;
                if (ch > 'Z') {
                    ch += (-'Z' + 'A' - 1);
                }
            }
            enmessage += ch;
        }
        return enmessage;
    }
    
    @Override
    public void editCourse(String code,int choice){
        CourseDto course = DataBase.courses.get(code);
        switch(choice){
            case 1:
                course.setName(input.next());
                break;
            case 2:
                course.setCode(input.next());
                break;
            case 3:
                course.setCourseSummary(input.next());
                break;
            case 4:
                course.setMaxStudent(input.nextInt());
                break;
            case 5:
                course.setPeriodAcc(input.nextInt());
                break;
        }
    }
}
