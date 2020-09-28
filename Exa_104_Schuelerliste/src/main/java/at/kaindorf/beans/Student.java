/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.beans;

import java.time.LocalDateTime;

/**
 *
 * @author kainz
 */
public class Student {
    private String name;
    private int catalognr;
    private String className;
    private String gender;
    private LocalDateTime birthdate;

    public Student() {
    }

    public Student(String name, int catalognr, String className, String gender, LocalDateTime birthdate) {
        this.name = name;
        this.catalognr = catalognr;
        this.className = className;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCatalognr() {
        return catalognr;
    }

    public void setCatalognr(int catalognr) {
        this.catalognr = catalognr;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
    }
    
    
}
