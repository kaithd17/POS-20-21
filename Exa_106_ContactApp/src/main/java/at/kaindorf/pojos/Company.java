/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author kainz
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company implements Comparable<Company>, Serializable{

    @EqualsAndHashCode.Include
    private String name;
    @EqualsAndHashCode.Include
    private String stockmarket;
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    private List<Contact> contacts;

    @Override
    public int compareTo(Company o) {
         return this.name.compareTo(o.getName());
    }

}
