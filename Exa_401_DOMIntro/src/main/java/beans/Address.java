/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author kainz
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String name;
    private String country;
    private String city;
    private String email;
    private String gender;
    private String timezone;
}
