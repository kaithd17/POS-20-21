/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author kainz
 */
public class IOHandler {
    
    public static List<String> getLanguage (String path) {
        try {
            List<String> languageList = new ArrayList<>();
            String[] stringArray = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)).readLine().split(";");
            languageList = Arrays.asList(stringArray);
            return languageList;
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
}
