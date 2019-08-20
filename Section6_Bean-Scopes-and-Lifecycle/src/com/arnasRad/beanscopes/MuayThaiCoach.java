package com.arnasRad.beanscopes;

public class MuayThaiCoach implements com.arnasRad.beanscopes.Coach {

    @Override
    public String getDailyWorkout() {
        return "Punch a heavy bag 10 rounds 3 minutes each";
    }

    @Override
    public String getDailyFortune() {
        return null;
    }

    public void init() {
        System.out.println("Starting MuayThaiCoach object init() method");
    }

    public void destroy() {
        System.out.println("Starting MuayThaiCoach object destroy() method");
    }
}
