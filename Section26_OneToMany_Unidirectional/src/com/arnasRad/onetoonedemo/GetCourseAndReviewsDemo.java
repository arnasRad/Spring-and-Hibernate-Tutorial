package com.arnasRad.onetoonedemo;

import com.arnasRad.onetoonedemo.entity.Course;
import com.arnasRad.onetoonedemo.entity.Instructor;
import com.arnasRad.onetoonedemo.entity.InstructorDetail;
import com.arnasRad.onetoonedemo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseAndReviewsDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();


        Session session = factory.getCurrentSession();
        try {
            // start a transaction
            session.beginTransaction();

            // get the course
            Course course = session.get(Course.class, 10);

            // print the course
            System.out.println(course);

            // print the course reviews
            System.out.println(course.getReviews());

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }

}
