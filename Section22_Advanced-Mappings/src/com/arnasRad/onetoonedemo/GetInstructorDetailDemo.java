package com.arnasRad.onetoonedemo;

import com.arnasRad.onetoonedemo.entity.Instructor;
import com.arnasRad.onetoonedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            // begin transaction
            session.beginTransaction();

            // get the instructor detail object
            InstructorDetail instructorDetail =
                    session.get(InstructorDetail.class, 4);

            // print the instructor detail
            System.out.println("tempInstructorDetail: " + instructorDetail);

            // print the associated instructor
            System.out.println("The associated instructor: " + instructorDetail.getInstructor());

            // commit the transaction
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
