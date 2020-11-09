/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.bl;

import at.kaindorf.beans.Stunde;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kainz
 */
public class SupplierplanBL {

    private Map<String, Stunde> timeTableMap = new LinkedHashMap<>();

    public List<Day> getAllDays() {
        List<Day> daysOfTheWeek = new ArrayList<>();
        daysOfTheWeek.add(Day.MO);
        daysOfTheWeek.add(Day.DI);
        daysOfTheWeek.add(Day.MI);
        daysOfTheWeek.add(Day.DO);
        daysOfTheWeek.add(Day.FR);
        return daysOfTheWeek;
    }

    public Map<String, Stunde> getTimeTableMap(List<Stunde> lessons, List<Day> daysOfTheWeek) {
        int counter = 0;
        int lessonCounter = 1;
        for (int i = 0; i < lessons.size(); i++) {
            if (counter % 5 == 0 && counter != 0) {
                counter = 0;
                lessonCounter++;
            }
            timeTableMap.put(daysOfTheWeek.get(counter).getDayToken() + lessonCounter, lessons.get(i));
            counter++;
        }
        return timeTableMap;
    }

    public Map<String, Stunde> addLesson(String day, String lesson, String teacher, String subject) {
        List<String> teachers = new ArrayList<>();
        teachers.add(teacher);
        timeTableMap.put(day + lesson, new Stunde(subject, teachers, true));
        return timeTableMap;
    }
}
