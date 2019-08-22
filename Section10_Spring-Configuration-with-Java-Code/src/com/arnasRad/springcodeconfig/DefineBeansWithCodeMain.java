package com.arnasRad.springcodeconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DefineBeansWithCodeMain {
    public static void main(String[] args) {
        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SportConfig.class);

        // retrieve bean from spring container
        Coach theCoach = context.getBean("swimCoach", com.arnasRad.springcodeconfig.Coach.class);

        // call a method on the bean
        System.out.println(theCoach.getDailyWorkout());

        // call FortuneService method
        System.out.println(theCoach.getDailyFortune());

        // close the application context
        context.close();
    }
}
