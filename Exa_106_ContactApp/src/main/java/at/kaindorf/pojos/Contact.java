/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.pojos;

import at.kaindorf.json.ContactSerializer;
import at.kaindorf.json.DateDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author kainz
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonSerialize(using = ContactSerializer.class)
public class Contact implements Serializable{
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.YYYY");

    private int id;
    private String firstname;
    private String lastname;
    private String[] email;
    private String gender;
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDate dateOfBirth;
    private Company company;
    @JsonIgnore
    private boolean favourite;
    
    public String formatDate(){
        return String.format("%s",DTF.format(dateOfBirth));
    }
    
    public long getAge() {
        return ChronoUnit.DAYS.between(dateOfBirth, LocalDate.now());
    }
    
    public Contact clone (){
        return new Contact(id,firstname,lastname,email,gender,dateOfBirth,company,favourite);
    }
}