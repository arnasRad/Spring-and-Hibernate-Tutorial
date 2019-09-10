package com.arnasRad.onetoonedemo;

import com.arnasRad.onetoonedemo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteMaryStudentDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();


        Session session = factory.getCurrentSession();
        try {
            // start a transaction
            session.beginTransaction();

            // get the student mary from database
            Student student = session.get(Student.class, 2);
            System.out.println("\nLoaded student: " + student);
            System.out.println("Course: " + student.getCourses());

            // delete student
            System.out.println("\nDeleting student: " + student);
            session.delete(student);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }

}
