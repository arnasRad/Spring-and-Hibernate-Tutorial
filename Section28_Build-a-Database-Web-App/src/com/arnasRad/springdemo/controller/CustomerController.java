package com.arnasRad.springdemo.controller;

import com.arnasRad.springdemo.entity.Customer;
import com.arnasRad.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        // create model attribute to bind form data
        Customer customer = new Customer();

        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {

        // save the customer using our service
        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId,
                                    Model model) {

        // get the customer from our customer service
        Customer customer = customerService.getCustomer(theId);

        // set customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);

        // send over to our form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {

        // delete the customer
        customerService.deleteCustomer(theId);

        // send over to our form
        return "redirect:list";
    }

    // add a mapping to handle search form submission
    @GetMapping("/search")
    public String searchCustomers(@RequestParam("searchName") String searchName,
                                  Model model) {

        // search customers from the service
        List<Customer> customers =
                customerService.searchCustomers(searchName);

        // add the customers to the model
        model.addAttribute("customers", customers);

        return "list-customers";
    }

    // add a mapping to handle list refresh
    @GetMapping("/refresh")
    public String refreshCustomers(Model model) {

        // get all customers from the service
        List<Customer> customers =
                customerService.getCustomers();

        // add the customers to the model
        model.addAttribute("customers", customers);

        return "list-customers";
    }
}
