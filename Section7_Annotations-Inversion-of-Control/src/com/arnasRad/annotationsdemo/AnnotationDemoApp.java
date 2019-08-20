package com.arnasRad.annotationsdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {
    public static void main(String[] args) {

        // read spring config file
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // get the bean from spring container
//        Coach theCoach = context.getBean("thatSillyCoach", Coach.class);
//        Coach theCoach = context.getBean("tennisCoach", Coach.class);
        Coach theCoach = context.getBean("footballCoach", Coach.class);

        // call a method on the bean
        System.out.println(theCoach.getDailyWorkout());

        // close the container
        context.close();
    }
}