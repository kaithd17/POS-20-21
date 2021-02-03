/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.jaxbIntro.test;

import jakarta.xml.bind.JAXB;
import at.kaindorf.jaxbIntro.beans.Student;
import at.kaindorf.jaxbIntro.beans.Exam;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author kainz
 */
public class JAXBTester {

    public static void main(String[] args) {

        try {
            Student student = new Student(1, "Adrian", "Berner");
            student.addExam(new Exam((short)1, "POS"));
            student.addExam(new Exam((short)1, "NVS"));
            //V1
            /*JAXBContext ctx = JAXBContext.newInstance(Student.class);
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(student, System.out);*/

            //V2
            //JAXB.marshal(student, System.out);
           JAXB.marshal(student, System.out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
