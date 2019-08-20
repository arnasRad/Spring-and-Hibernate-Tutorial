package com.arnasRad.annotationsdemo;

import org.springframework.stereotype.Component;

//@Component("thatSillyCoach")
@Component
public class TennisCoach implements Coach {

    @Override
    public String getDaiilyWorkout() {
        return "Practice your backhand volley";
    }
}
