/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.pojos;

import at.kaindorf.xml.DateAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.YYYY");

    private String title;
    private String link;
    private String description;
    private enclosure enclosure;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDateTime pubDate;

    public String formatDate() {
        return String.format("%s", pubDate.format(DTF));
    }
}
