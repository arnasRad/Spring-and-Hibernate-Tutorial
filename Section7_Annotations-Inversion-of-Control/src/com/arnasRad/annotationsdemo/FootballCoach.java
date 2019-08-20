package com.arnasRad.annotationsdemo;

import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Dribbling drills for 20 minutes";
    }
}
