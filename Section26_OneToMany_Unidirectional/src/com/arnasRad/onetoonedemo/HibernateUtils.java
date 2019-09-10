package com.arnasRad.onetoonedemo;

import com.arnasRad.onetoonedemo.entity.Course;
import com.arnasRad.onetoonedemo.entity.Instructor;
import com.arnasRad.onetoonedemo.entity.InstructorDetail;
import com.arnasRad.onetoonedemo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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

        // create session
        Session session = factory.getCurrentSession();
        try {
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
        } finally {
            session.close();
        }
    }

    public static void createCourse(SessionFactory factory,
                                    int instructorId,
                                    String courseName) {

        // create session
        Session session = factory.getCurrentSession();
        try {

            // start a transaction
            session.beginTransaction();

            // get the instructor from db
            Instructor instructor = session.get(Instructor.class, instructorId);

            // create some courses
            Course course = new Course(courseName);

            // add courses to instructor
            instructor.add(course);

            // save the courses
            session.save(course);

            // commit the transaction
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public static void deleteCourse(SessionFactory factory,
                                    int courseId) {

        // create session
        Session session = factory.getCurrentSession();
        try {

            // start a transaction
            session.beginTransaction();

            // get the instructor from db
            Course course = session.get(Course.class, courseId);

            // delete course
            System.out.println("Deleting course: " + course);

            // delete the course
            session.delete(course);

            // commit the transaction
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public static void createCourses(SessionFactory factory,
                                    int instructorId,
                                    String[] courseNames) {

        // create session
        Session session = factory.getCurrentSession();
        try {

            // start a transaction
            session.beginTransaction();

            // get the instructor from db
            Instructor instructor = session.get(Instructor.class, instructorId);

            Course courseObj;
            for(String course : courseNames) {
                // create some courses
                courseObj = new Course(course);

                // add courses to instructor
                instructor.add(courseObj);

                // save the courses
                session.save(courseObj);
            }

            // commit the transaction
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public static void createReviews(SessionFactory factory,
                                     int courseId,
                                     String[] reviewComments) {

        // create session
        Session session = factory.getCurrentSession();
        try {

            // start a transaction
            session.beginTransaction();

            // get the instructor from db
            Course course = session.get(Course.class, courseId);

            Review reviewObj;
            for(String review : reviewComments) {
                // create some courses
                reviewObj = new Review(review);

                // add courses to instructor
                course.add(reviewObj);

                // save the courses
                session.save(reviewObj);
            }

            // commit the transaction
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public static void getInstructorCourses(SessionFactory factory,
                                            int instructorId) {

        // create session
        Session session = factory.getCurrentSession();
        try {

            // start a transaction
            session.beginTransaction();

            // get the instructor from db
            Instructor instructor = session.get(Instructor.class, instructorId);
            System.out.println("luv2code: Instructor: " + instructor);

            // get course for the instructor
            System.out.println("luv2code: Courses: " + instructor.getCourses());

            // commit the transaction
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public static void getInstructorCoursesLazy(SessionFactory factory,
                                            int instructorId) {

        // create session
        Session session = factory.getCurrentSession();
        try {

            // start a transaction
            session.beginTransaction();

            // get the instructor from db
            Instructor instructor = session.get(Instructor.class, instructorId);
            System.out.println("luv2code: Instructor: " + instructor);

            System.out.println("luv2code: Courses: " + instructor.getCourses());

            // commit the transaction
            session.getTransaction().commit();

            // close the session
            session.close();

            System.out.println("\nluv2code: The session is now closed!\n");
            // option 1 (lazy error): call getter method while session is open

            // get course for the instructor
            System.out.println("luv2code: Courses: " + instructor.getCourses());

        } finally {
            session.close();
        }
    }

    public static void getInstructorCoursesLazyFetchJoin(SessionFactory factory,
                                            int instructorId) {

        // create session
        Session session = factory.getCurrentSession();
        try {

            // start a transaction
            session.beginTransaction();

            // option 2 (lazy error): Hibernate query with HQL

            // get the instructor from db
            // when executed, will load instructor and courses all at once
            Query<Instructor> query = session
                    .createQuery("select i from Instructor i " +
                            "JOIN FETCH i.courses " +
                            "where i.id=:theInstructorId",
                            Instructor.class);

            // set parameter on query
            query.setParameter("theInstructorId", instructorId);

            // execute query and get instructor
            Instructor instructor = query.getSingleResult();
            System.out.println("luv2code: Instructor: " + instructor);

            // commit the transaction
            session.getTransaction().commit();

            // close the session
            session.close();

            System.out.println("\nluv2code: The session is now closed!\n");

            // get course for the instructor
            System.out.println("luv2code: Courses: " + instructor.getCourses());

        } finally {
            session.close();
        }
    }

    public static void queryInstructors(SessionFactory factory) {

        // create session
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            // query for employee object
            List<Instructor> instructors = session.createQuery("from Instructor").getResultList();

            displayInstructors(instructors);

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
    
    public static void deleteAllInstructors(SessionFactory factory) {
        // create session
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            session.createQuery("delete from Instructor").executeUpdate();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public static void deleteAllInstructorDetails(SessionFactory factory) {
        // create session
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            session.createQuery("delete from InstructorDetail").executeUpdate();

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public static void deleteInstructorById(SessionFactory factory, int id) {
        // create session
        Session session = factory.getCurrentSession();
        try {
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
        } finally {
            session.close();
        }
    }
}
