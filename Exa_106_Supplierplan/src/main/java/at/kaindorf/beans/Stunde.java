/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.beans;

import java.util.List;

/**
 *
 * @author kainz
 */
public class Stunde {
    private String subject;
    private List<String> teachers;
    private boolean supplierung;

    public Stunde(String subject, List<String> teachers, boolean supplierung) {
        this.subject = subject;
        this.teachers = teachers;
        this.supplierung = supplierung;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<String> teachers) {
        this.teachers = teachers;
    }

    public boolean isSupplierung() {
        return supplierung;
    }

    public void setSupplierung(boolean supplierung) {
        this.supplierung = supplierung;
    }

    @Override
    public String toString() {
        return "Stunde{" + "subject=" + subject + ", teachers=" + teachers + ", supplierung=" + supplierung + '}';
    }
    
    
}
