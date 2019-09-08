package com.arnasRad.onetoonedemo;

import com.arnasRad.onetoonedemo.entity.Instructor;
import com.arnasRad.onetoonedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HibernateUtils {

    public static void saveInstructors(List<Instructor> instructors, Session session) {
        for(Instructor instructor : instructors) {
            session.save(instructor);
        }
    }

    public static void displayInstructors(List<Instructor> instructors) {
        for (Instructor instructor : instructors) {
            System.out.println(instructor);
        }
    }

    public static void createInstructor(SessionFactory factory,
                                         String firstName, String lastName,
                                         String email, String youtubeChannel,
                                         String hobby) {
        // create an Instructor object
        System.out.println("Creating a new Instructor object...");

        // create the objects
        Instructor instructor =
                new Instructor(firstName, lastName, email);

        InstructorDetail instructorDetail =
                new InstructorDetail(youtubeChannel,
                        hobby);

        // associate the objects
        instructor.setInstructorDetail(instructorDetail);

        // create session
        Session session = factory.getCurrentSession();
        // start a transaction
        session.beginTransaction();
        // save the instructor
        //
        // Note: this will ALSO save the details object
        // because of CascadeType.ALL
        //
        System.out.println("Saving instructor: " + instructor);
        session.save(instructor);

        // commit the transaction
        session.getTransaction().commit();
    }

    public static void queryInstructors(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // query for employee object
        List<Instructor> instructors = session.createQuery("from Instructor").getResultList();

        displayInstructors(instructors);

        session.getTransaction().commit();
    }
    
    public static void deleteAllInstructors(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        session.createQuery("delete from Instructor").executeUpdate();

        session.getTransaction().commit();
    }

    public static void deleteAllInstructorDetails(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        session.createQuery("delete from InstructorDetail").executeUpdate();

        session.getTransaction().commit();
    }

    public static void deleteInstructorById(SessionFactory factory, int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        System.out.println("Deleting Instructor by id...");

        // get instructor by primary key / id
        Instructor instructor = session.get(Instructor.class, id);

        System.out.println("Found instructor: " + instructor);

        // delete the instructor
        if (instructor != null) {
            System.out.println("Deleting " + instructor);

            // Note: will ALSO delete associated "details" object
            // because of CascadeType.ALL
            session.delete(instructor);
        }

        session.getTransaction().commit();
    }
}
