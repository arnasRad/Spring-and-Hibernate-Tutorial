package com.arnasRad.onetoonedemo;
import com.arnasRad.onetoonedemo.entity.Course;
import com.arnasRad.onetoonedemo.entity.Instructor;
import com.arnasRad.onetoonedemo.entity.InstructorDetail;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static com.arnasRad.onetoonedemo.HibernateUtils.createInstructor;

public class CreateInstructorDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();


        try {

            createInstructor(
                    factory,
                    "Susan",
                    "Public",
                    "susan@luv2code.com",
                    "http://www.youtube.com",
                    "Video Games"
            );

            createInstructor(
                    factory,
                    "Arnas",
                    "Radzevicius",
                    "arnas@gmail.com",
                    "http://www.youtube.com",
                    "coding"
            );

//            HibernateUtils.deleteInstructorById(factory, 5);

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
}
