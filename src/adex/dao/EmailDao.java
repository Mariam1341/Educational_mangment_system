/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dao;

import adex.dto.EmailDto;
import adex.dto.User;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Vega Laptop
 */
public class EmailDao {
    
    public static boolean validEmail(String email){
        int valid1 = 0;
        ArrayList arr = new ArrayList();
        for(int i = 0; i < email.length(); i++){
            int num = (int)email.charAt(i);
            if(num >= 65 && num <= 122){
                valid1++;
            }if (num == 64){
                for(int j = i; j < email.length(); j++){                   
                    arr.add(email.charAt(j));
                }break;
            }
        }
        if(!(valid1 >= 6)){
           return false; 
        }else{
            
            if (arr.size() == 10){
                String valid2 = "@gmail.com";
                for(int j = 0; j < valid2.length(); j++){
                    if(!(arr.get(j).equals(valid2.charAt(j)))){
                        return false;
                    }
                }
            }else{
                return false;
            }
        }
          
        return true;
    }
    //String fromE, String toE, String fromName, String toName, String content, String title
    public void send(User user,String toEmail,String content, String title){
        EmailDto email = new EmailDto(user.getEmail(), toEmail, user.getUserName(), DataBase.emails.get(toEmail).getUserName(), content, title);
        DataBase.emails.get(toEmail).getInbox().put(title, email);
        DataBase.emails.get(user.getEmail()).getInbox().put(title, email);
    }
    public void replay(EmailDto email,String content){
        EmailDto e = new EmailDto(email.getToE(),email.getFromE(),email.getToName(),email.getFromName(),content,email.getTitle());
        email.getReplys().add(e);
    }
}
