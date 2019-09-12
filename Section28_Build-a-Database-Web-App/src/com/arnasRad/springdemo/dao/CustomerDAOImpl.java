package com.arnasRad.springdemo.dao;

import com.arnasRad.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
//    @Transactional
    public List<Customer> getCustomers() {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query
        // sort by last name
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by lastName",
                        Customer.class);

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {

        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save the customer
//        currentSession.save(customer);
        // save/update the customer
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int theId) {

        // get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // retrieve / read from database using the primary key
        Customer customer = session.get(Customer.class, theId);

        // return the customer
        return customer;
    }
}
