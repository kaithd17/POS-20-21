/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.bl;

/**
 *
 * @author kainz
 */
public enum Day {
    MO("Mo","Montag"), DI("Di", "Dienstag"), MI("Mi", "Mittwoch"), DO("Do", "Donnerstag"), FR("Fr", "Freitag"); 

    private String dayToken;
    private String nameOfDay;

    private Day(String dayToken, String nameOfDay) {
        this.dayToken = dayToken;
        this.nameOfDay = nameOfDay;
    }

    public String getNameOfDay() {
        return nameOfDay;
    }

    public void setNameOfDay(String nameOfDay) {
        this.nameOfDay = nameOfDay;
    }

    public String getDayToken() {
        return dayToken;
    }

    public void setDayToken(String dayToken) {
        this.dayToken = dayToken;
    }
}
