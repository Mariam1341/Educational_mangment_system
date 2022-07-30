/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.bao;

/**
 *
 * @author Vega Laptop
 */
public class BaoFactory {
    public AssignmentBao getAssignmentBao(){
        return (AssignmentBao) new AssignmentBaoImpl();
    }
    public DoctorBao getDoctorBao(){
        return (DoctorBao) new DoctorBaoImpl();
    }
    public StudentBao getStudentBao(){
        return (StudentBao) new StudentBaoImpl();
    }
    public TABao getTABao(){
        return (TABao) new TABaoImpl();
    }
}
