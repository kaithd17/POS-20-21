/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import java.util.Arrays;
import json.LocalDateDeserializer;
import json.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author kainz
 */
@Data // schortcut for @Getter, @Setter, @ToString
@AllArgsConstructor
@NoArgsConstructor
public class Car {

	private String brand, type;
	// @EqualsAndHashCode.Exclude
	// @ToString.Exclude
	//@JsonIgnore
	private double weight;
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        private LocalDate ld;

	public static void main(String[] args) {
		Car c1 = new Car("vw", "polo", 1234, LocalDate.now());
		Car c2 = new Car();
		System.out.println(c1);
		System.out.println(c2);

		Car[] cars = {c1, c2};

		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(c1);
			// String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cars);
			String json2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cars);
			System.out.println(json);
			
//			System.out.println("JSON parsing");
//			String carJSON = json;
//			Car carFromJSON = mapper.readValue(carJSON, Car.class);
//			System.out.println(carFromJSON);
//			
//			String carArray = json2;
//			Car[] carsFromJSON = mapper.readValue(carArray, Car[].class);
//			System.out.println(Arrays.toString(carsFromJSON));

		} catch (JsonProcessingException ex) {
			System.out.println(ex);
		}
	}
}