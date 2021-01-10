/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 *
 * @author kainz
 */
public class DateDeserializer extends StdDeserializer<LocalDate> {

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

    public DateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        try{
            return LocalDate.parse(jp.getText(), DTF);
        }catch(DateTimeParseException ex){
            return null;
        }
        
    }

}
