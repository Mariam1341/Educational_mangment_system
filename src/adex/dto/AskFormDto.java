/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dto;

/**
 *
 * @author Vega Laptop
 */
public class AskFormDto {
    private boolean isAccept = false;
    private boolean watting = true;
    private boolean TAass = false;
    private CourseDto course;
    private String from;
    private String to;
    

    public AskFormDto(CourseDto course, String from, String to) {
        this.course = course;
        this.from = from;
        this.to = to;
    }
    public AskFormDto(CourseDto course, String from, String to, boolean TAass) {
        this.course = course;
        this.from = from;
        this.to = to;
        this.TAass = TAass;
    }

    public String isIsAccept() {
        if(isAccept){
            return "Accepted";
        }else{
            if(watting){
                return "Watting";
            }else{
                return "Not Accepted";
            }            
        }
    }

    public void setIsAccept(boolean isAccept) {
        this.isAccept = isAccept;
        this.watting = false;
    }

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
}
