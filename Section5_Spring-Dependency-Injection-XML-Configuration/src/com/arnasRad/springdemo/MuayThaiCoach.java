package com.arnasRad.springdemo;

public class MuayThaiCoach implements com.arnasRad.springdemo.Coach {
    @Override
    public String getDailyWorkout() {
        return "Punch a heavy bag 10 rounds 3 minutes each";
    }

    @Override
    public String getDailyFortune() {
        return null;
    }
}
