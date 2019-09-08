package com.arnasRad.practiceactivity;

import com.arnasRad.practiceactivity.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();


        try {
//            saveObjects(factory);

//            queryEmployees(factory);
//            queryEmployeeById(factory, "Parchutikas");
//            queryEmployeesByCompany(factory, "Visma");

//            deleteEmployeeById(factory, "Kisielis");
            queryEmployees(factory);

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }

    private static void saveEmployees(List<Employee> employees, Session session) {
        for(Employee employee : employees) {
            session.save(employee);
        }
    }

    private static void displayEmployees(List<Employee> employees) {
        for(Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private static void saveObjects(SessionFactory factory) {
        // create an employee object
        System.out.println("Creating a new Employee object...");
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Laura", "Kutovaja", "Visma"));
        employees.add(new Employee("Vardenis", "Pavardenis", "Kompanita"));
        employees.add(new Employee("Axel", "Rose", "Guns n' Roses"));
        employees.add(new Employee("Kęstutis", "Parchutikas", "Visma"));
        employees.add(new Employee("Viktoras", "Kisielis", "Big Bank"));

        // create session
        Session session = factory.getCurrentSession();
        // start a transaction
        session.beginTransaction();

        // save the employee object
        System.out.println("Saving Employees...");
        saveEmployees(employees, session);

        session.getTransaction().commit();
    }

    private static void queryEmployees(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // query for employee object
        List<Employee> employees = session.createQuery("from Employee").getResultList();

        displayEmployees(employees);

        session.getTransaction().commit();
    }

    private static void queryEmployeesByCompany(SessionFactory factory, String company) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // query for employee object
        List<Employee> employees = session.createQuery("from Employee where company='" + company + "'").getResultList();

        displayEmployees(employees);

        session.getTransaction().commit();
    }

    private static void queryEmployeeById(SessionFactory factory, String id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // get object by id
        Employee employee1 = session.get(Employee.class, id);
        System.out.println(employee1);

        session.getTransaction().commit();
    }

    private static void deleteAllEmployees(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        session.createQuery("delete from Employee").executeUpdate();

        session.getTransaction().commit();
    }

    private static void deleteEmployeeById(SessionFactory factory, String id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        // delete employee by primary id
        Employee roseEmployee = session.get(Employee.class, id);
        session.delete(roseEmployee);

        session.getTransaction().commit();
    }
}
