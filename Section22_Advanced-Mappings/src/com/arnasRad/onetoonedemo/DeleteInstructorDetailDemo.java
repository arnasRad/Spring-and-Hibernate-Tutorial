package com.arnasRad.onetoonedemo;

import com.arnasRad.onetoonedemo.entity.Instructor;
import com.arnasRad.onetoonedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try {
            // begin transaction
            session.beginTransaction();

            // get the instructor detail object
            InstructorDetail instructorDetail =
                    session.get(InstructorDetail.class, 6);

            // print the instructor detail
            System.out.println("tempInstructorDetail: " + instructorDetail);

            // print the associated instructor
            System.out.println("The associated instructor: " + instructorDetail.getInstructor());

            // now let's delete the instructor detail
            System.out.println("Deleting instructorDetail: " + instructorDetail);
            // remove the associated object reference
            // break bi-directional link

            instructorDetail.getInstructor().setInstructorDetail(null);

            session.delete(instructorDetail);

            // commit the transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // handle connection leak issue
            session.close();

            factory.close();
        }
    }
}
