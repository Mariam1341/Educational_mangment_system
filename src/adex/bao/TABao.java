/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.bao;

import adex.dto.TADto;

/**
 *
 * @author Vega Laptop
 */
public interface TABao {
    public TADto login();
    public TADto signup();
    public void listmyCourses(TADto ta);
    public boolean createAss(TADto ta,String courseCode);
    public void askForTA(TADto ta);
    public void listmyInv(TADto ta);
    public void acceptInv(TADto ta);
    
}
