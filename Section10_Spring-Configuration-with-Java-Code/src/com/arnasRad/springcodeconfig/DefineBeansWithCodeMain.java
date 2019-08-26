package com.arnasRad.springcodeconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DefineBeansWithCodeMain {
    public static void main(String[] args) {
        // read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SportConfig.class);

        // retrieve bean from spring container
//        Coach theCoach = context.getBean("swimCoach", Coach.class);
        SwimCoach theCoach = context.getBean("swimCoach", SwimCoach.class);

        // call a method on the bean
        System.out.println(theCoach.getDailyWorkout());

        // call FortuneService method
        System.out.println(theCoach.getDailyFortune());

        // call our new swim coach methods ... has the props values injected
        System.out.println("email: " + theCoach.getEmail());
        System.out.println("team: " + theCoach.getTeam());

        // close the application context
        context.close();
    }
}
