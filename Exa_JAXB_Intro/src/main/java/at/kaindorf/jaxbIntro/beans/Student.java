/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.jaxbIntro.beans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 *
 * @author kainz
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Student {

    @NonNull
    private int catalogNo;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    @EqualsAndHashCode.Exclude
    @XmlElementWrapper(name = "exams")
    @XmlElement(name = "exam")
    private List<Exam> exams = new ArrayList<>();

    public void addExam(Exam exam) {
        exams.add(exam);
    }
}
