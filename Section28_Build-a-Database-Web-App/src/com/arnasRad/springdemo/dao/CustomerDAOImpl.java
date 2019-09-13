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

    @Override
    public void deleteCustomer(int theId) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // delete object with primary key
        Query theQuery =
                currentSession.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", theId);

        theQuery.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String searchName) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // search customers by last name
        Query query = null;

        // only search if searchName is not empty
        if (searchName != null && searchName.trim().length() > 0) {
            // search the first name or the last name
            query = currentSession.createQuery("from Customer " +
                    "where lower(firstName) like :theName " +
                    "or lower(lastName) like :theName", Customer.class);
            query.setParameter("theName", "%" + searchName.toLowerCase() + "%");
        } else {
            // searchName is empty ... so just get all customers
            query = currentSession.createQuery("from Customer", Customer.class);
        }

        // execute query and get the result list
        List<Customer> customers = query.getResultList();

        // return customers
        return customers;
    }
}
