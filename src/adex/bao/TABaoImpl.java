/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.bao;

import adex.dao.DaoFactory;
import adex.dao.DataBaseImpl;
import adex.dao.TADao;
import adex.dto.TADto;
import java.util.Scanner;

/**
 *
 * @author Vega Laptop
 */
public class TABaoImpl implements TABao{
    Scanner input = new Scanner(System.in);
    DaoFactory fac = new DaoFactory();
    TADao TA = fac.getTADao();
    
    @Override
    public TADto signup(){
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
        if(TA.search(ID)){
           return TA.signup(userName, ID, password, fullName, email); 
       }else{
           System.out.println("You have already an account... LOG IN");
           return login();
       }
    }
    
    @Override
    public TADto login(){
        System.out.println("  Enter User Name: ");
        String userName = input.nextLine();
        System.out.println("  Enter password: ");
        String password = input.nextLine();
        if(TA.login(userName, password) == null){
            signup();
        }           
        return TA.login(userName, password);
    }
    
    @Override
    public void listmyCourses(TADto ta){
        TA.listmyCourses(ta);
    }
    public void viewCourse(){
        
    }
    @Override
    public boolean createAss(TADto ta,String courseCode){
        return TA.couldCreateAss(ta, courseCode);
    }
    @Override
    public void askForTA(TADto ta){
        TA.listallCourses();
        System.out.println("Enter  the course code:");
        String code = input.next();
        TA.askForTA(code, ta);
    }
    
    public void listmyInv(TADto ta){
        TA.listMyInv(ta);
    }
    public void acceptInv(TADto ta){
        listmyInv(ta);
        System.out.println("Enter  Doctor Name:");
        String nameD = input.next();
        TA.acceptInv(ta, nameD);
    }
    
}
