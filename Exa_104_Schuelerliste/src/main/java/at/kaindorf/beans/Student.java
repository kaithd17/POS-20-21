/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author kainz
 */
public class Student {

    private String firstname;
    private String lastname;
    private int catalognr;
    private String className;
    private String gender;
    private LocalDate birthdate;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Student() {
    }

    public Student(String firstname, String lastname, int catalognr, String className, String gender, LocalDate birthdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.catalognr = catalognr;
        this.className = className;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public static Student getStudent(String line) {
        //4DHIF;KAINZ;Thomas;m;2002-01-01
        String className = line.split(";")[0];
        String lastname = line.split(";")[1];
        String firstname = line.split(";")[2];
        String gender = line.split(";")[3];
        LocalDate birthdate = LocalDate.parse(line.split(";")[4], DTF);
        int catalognr = 0;
        return new Student(firstname, lastname, catalognr,className, gender, birthdate);
    }

}
