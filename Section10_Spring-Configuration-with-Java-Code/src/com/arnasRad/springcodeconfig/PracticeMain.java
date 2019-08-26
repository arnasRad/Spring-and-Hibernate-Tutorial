package com.arnasRad.springcodeconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PracticeMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(BoxingConfig.class);

        BoxingCoach boxingCoach = context.getBean("boxingCoach", BoxingCoach.class);

        System.out.println(boxingCoach.getDailyWorkout());
        System.out.println(boxingCoach.getDailyFortune());

        context.close();
    }
}
