/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.pojos;

import at.kaindorf.json.DateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;
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
public class Contact {
    private int id;
    private String firstname;
    private String lastname;
    private String[] email;
    private String gender;
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDate dateOfBirth;
    private Company company;
}
