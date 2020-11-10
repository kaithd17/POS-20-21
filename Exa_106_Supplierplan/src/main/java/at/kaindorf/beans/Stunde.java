/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public String formatTeachers() {
        String str = "";
        for (int i = 0; i < teachers.size(); i++) {
            if ((i + 1) == teachers.size()) {
                str += teachers.get(i);
            } else {
                str += teachers.get(i) + ", ";
            }
        }
        return str;
    }

    public static Stunde getLesson(String line) {
        //RK;PA SYP1U;KW,BA -;-
        List<String> teachers = new ArrayList<>();
        if (line.contains("-")) {
            return new Stunde("", teachers, false);
        } else {
            String subject = line.split(";")[0];
            String[] teachersArray = line.split(";")[1].split(",");
            for (int i = 0; i < teachersArray.length; i++) {
                teachers.add(teachersArray[i]);
            }
            return new Stunde(subject, teachers, false);
        }

    }
}
