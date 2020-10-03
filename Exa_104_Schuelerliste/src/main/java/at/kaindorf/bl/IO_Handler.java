/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.bl;

import at.kaindorf.beans.Student;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author kainz
 */
public class IO_Handler {
    
    public static List<Student> getAllStudents(String path) throws FileNotFoundException{
        List<Student> studentList = new ArrayList<>();
        try{
            studentList = new BufferedReader(new FileReader(path)).lines().skip(1).map(Student::getStudent).collect(Collectors.toList());
        }catch(IOException e){
            
        }
        return studentList;
    }
}
