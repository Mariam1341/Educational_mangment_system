/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dao;


import adex.dto.AskFormDto;
import adex.dto.TADto;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Vega Laptop
 */
public class TADaoImpl implements TADao{
    Scanner input = new Scanner(System.in);
    @Override
    public TADto signup(String userName, String ID, String password, String fullName, String email) {
        TADto ta = new TADto(userName, ID, password, fullName, email);
        DataBase.emails.put(email, ta);
        DataBaseImpl.TAs.put(userName, ta);
        return ta;
    }

    
    @Override
    public TADto login(String userName,String password) {
        Set<String> keys = DataBaseImpl.TAs.keySet();
        for(String key : keys){
            if(key.equals(userName)){
                if(DataBaseImpl.TAs.get(key).getPassword().equals(password)){
                    return DataBaseImpl.TAs.get(key);
                }else{
                    System.out.println("You enter wrong password try again:  ");
                    if(password.equals(input.next())){
                        return DataBaseImpl.TAs.get(key);
                    }else{
                        System.out.println("You enter wrong password try to login with your ID./nEnter your ID:  ");
                        if(DataBaseImpl.TAs.get(key).getID().equals(input.next())){
                            return DataBaseImpl.TAs.get(key);
                        }
                    }                  
                }
            }
        }
        System.out.println("\n**Not found account, you have to sign up:\n");
        return null;
    }
    
    
    
    @Override
    public void listMyInv(TADto ta){
        Set<String> keys = ta.getInvitationsC().keySet();
        int num =0;
        for(String key : keys){
            num++;
            System.out.println(num+"Doctor: "+ta.getInvitationsC().get(key).getFrom()+" invite you to Course: "+ta.getInvitationsC().get(key).getCourse().getCode());
        }
    }
    
    @Override
    public void acceptInv(TADto ta, String nameD){
        ta.getInvitationsC().get(nameD).setIsAccept(true);
        DataBase.doctors.get(nameD).getMyTAs().put(ta.getUserName(), ta);
        ta.getInvitationsC().get(nameD).getCourse().getTAs().put(ta.getUserName(), ta);
    }
    
    @Override
    public boolean couldCreateAss(TADto ta, String courseCode){
        return ta.getCourseAss().containsKey(courseCode);
    }
    
    @Override
    public void listmyCourses(TADto ta){
        Set<String> keys = ta.getCourses().keySet();
        int num =0;
        for(String key : keys){
            num++;
            System.out.println(num+") Code: "+ta.getCourses().get(key).getCode()+" Course: "+ta.getCourses().get(key).getName());
        }
    }
    
    @Override
    public boolean search(String ID){
        Set<String> keysS = DataBaseImpl.TAs.keySet();
        for(String key : keysS){
            if(ID.equals(DataBaseImpl.TAs.get(key).getID())){
                return false;
            }
        }
        /*
            if (!keysS.stream().noneMatch((key) -> (ID.equals(DataBaseImpl.TAs.get(key).getID())))) {
            return false;
        }
        */
        return true;
    }
    
    @Override
    public void listallCourses(){
         Set<String> keys = DataBase.courses.keySet();
        int num =0;
        for(String key : keys){
            num++;
            System.out.println(num+") Code: "+DataBase.courses.get(key).getCode()+" Course: "+DataBase.courses.get(key).getName());
        }
    }
    
    @Override
    public void askForTA(String courseCode, TADto ta){
        AskFormDto ask = new AskFormDto(DataBase.courses.get(courseCode),ta.getFullName(),DataBase.courses.get(courseCode).getDoctorName());
        DataBase.doctors.get(DataBase.courses.get(courseCode).getDoctorName()).getAskTAs().put(ta.getUserName(), ask);
    }
    
    
    
}
