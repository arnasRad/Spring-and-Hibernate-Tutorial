package com.arnasRad.onetoonedemo;
import com.arnasRad.onetoonedemo.entity.Instructor;
import com.arnasRad.onetoonedemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CreateDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();


        try {

            createInstructor(
                    factory,
                    "Madhu",
                    "Patel",
                    "madhu@luv2code.com",
                    "http://www.youtube.com",
                    "Guitar"
            );

//            deleteAllInstructors(factory);
//            deleteAllInstructorDetails(factory);

//            saveObjects(factory);

//            queryEmployees(factory);
//            queryEmployeeById(factory, "Parchutikas");
//            queryEmployeesByCompany(factory, "Visma");

//            deleteEmployeeById(factory, "Kisielis");
//            queryInstructors(factory);

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }

    private static void saveInstructors(List<Instructor> instructors, Session session) {
        for(Instructor instructor : instructors) {
            session.save(instructor);
        }
    }

    private static void displayInstructors(List<Instructor> instructors) {
        for (Instructor instructor : instructors) {
            System.out.println(instructor);
        }
    }

    private static void createInstructor(SessionFactory factory,
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

    private static void queryInstructors(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // query for employee object
        List<Instructor> instructors = session.createQuery("from Instructor").getResultList();

        displayInstructors(instructors);

        session.getTransaction().commit();
    }

//    private static void queryEmployeesByCompany(SessionFactory factory, String company) {
//        Session session = factory.getCurrentSession();
//        session.beginTransaction();
//
//        // query for employee object
//        List<Employee> employees = session.createQuery("from Employee where company='" + company + "'").getResultList();
//
//        displayEmployees(employees);
//
//        session.getTransaction().commit();
//    }
//
//    private static void queryEmployeeById(SessionFactory factory, String id) {
//        Session session = factory.getCurrentSession();
//        session.beginTransaction();
//
//        // get object by id
//        Employee employee1 = session.get(Employee.class, id);
//        System.out.println(employee1);
//
//        session.getTransaction().commit();
//    }
//
    private static void deleteAllInstructors(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        session.createQuery("delete from Instructor").executeUpdate();

        session.getTransaction().commit();
    }

    private static void deleteAllInstructorDetails(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        session.createQuery("delete from InstructorDetail").executeUpdate();

        session.getTransaction().commit();
    }
//
//    private static void deleteEmployeeById(SessionFactory factory, String id) {
//        Session session = factory.getCurrentSession();
//        session.beginTransaction();
//
//        // delete employee by primary id
//        Employee roseEmployee = session.get(Employee.class, id);
//        session.delete(roseEmployee);
//
//        session.getTransaction().commit();
//    }
}
