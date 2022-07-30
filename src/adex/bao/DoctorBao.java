/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.bao;

import adex.dto.DoctorDto;

/**
 *
 * @author Vega Laptop
 */
public interface DoctorBao {
    public DoctorDto signup();
    public DoctorDto login();
    public void createCourse(DoctorDto doc);
    public void createPrivCourse(DoctorDto doc);
    public void listMyCourses(DoctorDto doc);
    public void editCourse(DoctorDto doc);
}
