/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.json;

import com.fasterxml.jackson.databind.JsonSerializer;
import at.kaindorf.pojos.Contact;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author kainz
 */
public class ContactSerializer extends JsonSerializer<Contact> {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void serialize(Contact t, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeStartObject();
        jg.writeNumberField("id", t.getId());
        jg.writeStringField("firstname", t.getFirstname());
        jg.writeStringField("lastname", t.getLastname());

        jg.writeFieldName("email");
        jg.writeStartArray();
        for (String email : t.getEmail()) {
            jg.writeString(email);
        }
        jg.writeEndArray();

        jg.writeStringField("gender", t.getGender());
        jg.writeStringField("dateOfBirth", DTF.format(t.getDateOfBirth()));

        jg.writeFieldName("company");
        jg.writeStartObject();
        jg.writeStringField("name", t.getCompany().getName());
        jg.writeStringField("stockmarket", t.getCompany().getStockmarket());
        jg.writeEndObject();

        jg.writeEndObject();
    }

}
