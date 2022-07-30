/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adex.dto;

import java.util.HashMap;
import java.util.*;

/**
 *
 * @author Vega Laptop
 */
public class EmailDto {
    private String fromE;
    private String toE;
    private String fromName;
    private String toName;
    private String content;
    private String title;
    private ArrayList< EmailDto> replys = new ArrayList();

    public EmailDto(String fromE, String toE, String fromName, String toName, String content, String title) {
        this.fromE = fromE;
        this.toE = toE;
        this.fromName = fromName;
        this.toName = toName;
        this.content = content;
        this.title = title;
    }

    

    public String getFromE() {
        return fromE;
    }

    public String getToE() {
        return toE;
    }

    public String getFromName() {
        return fromName;
    }

    public String getToName() {
        return toName;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<EmailDto> getReplys() {
        return replys;
    }
    
    
}
