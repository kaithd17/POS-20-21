/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.io;

import at.kaindorf.beans.Stunde;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author kainz
 */
public class IO_Handler {

    public static String getClassName(String path) throws IOException {
        String className = "";
        try {
            className = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)).readLine().split(";")[0];
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
        return className;
    }

    public static List<Stunde> getAllLessons(String path) {
        List<Stunde> lessonsList = new ArrayList<>();
        try {
            lessonsList = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)).lines().skip(1).map(Stunde::getLesson).collect(Collectors.toList());
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
        return lessonsList;
    }
}
