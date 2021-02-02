/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.thecrether.jaxb_intro.test;

import at.thecrether.jaxb_intro.pojos.Student;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXB;
import at.thecrether.jaxb_intro.pojos.Exam;

/**
 *
 * @author TheCrether <thecrether.at>
 */
public class JAXBTester {

    public static void main(String[] args) {
        Student student = new Student(2, "Adrian", "Berner");
        student.addExam(new Exam((short) 1, "lol"));
        student.addExam(new Exam((short) 2, "asdkhj"));
        try {
            // Variante-1: Serializieren
//			JAXBContext context = JAXBContext.newInstance(Student.class);
//			Marshaller marshaller = context.createMarshaller();
//			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			marshaller.marshal(student, System.out);
            JAXB.marshal(student, System.out);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
