/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.thecrether.jaxb_intro.pojos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElementWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 *
 * @author TheCrether <thecrether.at>
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

	@NonNull
	private int catalogNo;
	@NonNull
	private String firstname;
	@NonNull
	private String lastname;
	@EqualsAndHashCode.Exclude
	@XmlElementWrapper(name="exams")
	@XmlElement(name="exam")
	private List<Exam> exams = new ArrayList<>();

	public void addExam(Exam exam) {
		exams.add(exam);
	}
}
