package com.arnasRad.onetoonedemo;

import com.arnasRad.onetoonedemo.entity.Course;
import com.arnasRad.onetoonedemo.entity.Instructor;
import com.arnasRad.onetoonedemo.entity.InstructorDetail;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static com.arnasRad.onetoonedemo.HibernateUtils.getInstructorCourses;

public class GetInstructorCoursesDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();


        try {
            getInstructorCourses(factory, 1);

            System.out.println("Done!");
        } finally {

            factory.close();
        }
    }

}
