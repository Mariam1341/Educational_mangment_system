/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dao;

import adex.dto.TADto;

/**
 *
 * @author Vega Laptop
 */
public interface TADao {
    public void listMyInv(TADto ta);
    public void acceptInv(TADto ta, String nameD);
    public boolean couldCreateAss(TADto ta, String courseCode);
    public void listmyCourses(TADto ta);
    public boolean search(String ID);
    public TADto signup(String userName, String ID, String password, String fullName, String email) ;
    public TADto login(String userName,String password) ;
    public void listallCourses();
    public void askForTA(String courseCode, TADto ta);
}
