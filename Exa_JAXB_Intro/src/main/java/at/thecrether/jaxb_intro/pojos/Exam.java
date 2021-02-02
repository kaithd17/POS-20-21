/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.thecrether.jaxb_intro.pojos;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author TheCrether <thecrether.at>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "exam")
public class Exam {
	private short mark;
	private String name;
}
