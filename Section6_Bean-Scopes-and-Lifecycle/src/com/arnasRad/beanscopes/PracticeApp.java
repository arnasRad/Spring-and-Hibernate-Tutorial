package com.arnasRad.beanscopes;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PracticeApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("practice-applicationContext.xml");

        Coach myCoach = context.getBean("myCoach", MuayThaiCoach.class);
        Coach alphaCoach = context.getBean("myCoach", MuayThaiCoach.class);

        boolean result = myCoach == alphaCoach;

        System.out.println("myCoach and alphaCoach are pointing to the same reference: " + result);
        System.out.println("myCoach: " + myCoach);
        System.out.println("alphaCoach: " + alphaCoach);

        context.close();
    }

}
