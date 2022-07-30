/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vega Laptop
 */
public class User {
    private static String userName;
    private static String ID;
    private String password;
    private static String fullName;
    private String email;
    private Map<String, EmailDto> inbox = new HashMap();
    
    public User(){}
    
    public User(String userName, String ID) {
   	 this.userName = userName;
         this.ID = ID;
    }
//    
    public User(String userName, String ID, String password, String fullName, String email) {
        this.userName = userName;
        this.ID = ID;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, EmailDto> getInbox() {
        return inbox;
    }
    
}


