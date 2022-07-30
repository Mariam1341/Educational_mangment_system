/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dao;



/**
 *
 * @author Vega Laptop
 */
public class DaoFactory {
    public  DoctorDao getDoctorDao(){
        return (DoctorDao) new DoctorDaoImpl();
    }
    public  StudentDao getStudentDao(){
        return (StudentDao) new StudentDaoImpl();
    }
    public AssignmentDao getAssignmentDao(){
        return (AssignmentDao) new AssignmentDaoImpl();
    }
    public DataBase getData(){
        return (DataBase) new DataBaseImpl();
    }
    public TADao getTADao(){
        return (TADao) new TADaoImpl();
    }
}
