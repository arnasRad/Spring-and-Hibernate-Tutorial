package com.arnasRad.aopdemo;

import com.arnasRad.aopdemo.dao.AccountDAO;
import com.arnasRad.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {
    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from spring container
        AccountDAO accountDAO =
                context.getBean("accountDAO", AccountDAO.class);

        // get membership bean from spring container
        MembershipDAO membershipDAO =
                context.getBean("membershipDAO", MembershipDAO.class);

        // call the business method
        accountDAO.addAccount();

        // call the membership business method
        membershipDAO.addAccount();

        // do it again
        System.out.println("\nlet's call addAccount() again!");
        accountDAO.addAccount();

        // close the context
        context.close();
    }
}
