package com.arnasRad.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PracticeApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("practiceApplicationContext.xml");

        CricketCoach myCoach = context.getBean("myCoach", CricketCoach.class);

        System.out.println(myCoach.getEmailAddress());
        System.out.println(myCoach.getTeam());
        System.out.println(myCoach.getDailyFortune());

        context.close();
    }
}
