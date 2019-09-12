package com.arnasRad.springdemo.controller;

import com.arnasRad.springdemo.dao.CustomerDAO;
import com.arnasRad.springdemo.entity.Customer;
import com.arnasRad.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    // need to inject the customer dao
    // Spring will scan for a component that implements CustomerDAO interface
//    @Autowired
//    private CustomerDAO customerDAO;

    // need to inject our customer service
    @Autowired
    private CustomerService customerService;

//    @RequestMapping("/list")
    // only responds to GET request
    @GetMapping("/list")
    public String listCustomers(Model theModel) {

        // get customer from the service
        List<Customer> theCustomers = customerService.getCustomers();

        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";
    }
}
