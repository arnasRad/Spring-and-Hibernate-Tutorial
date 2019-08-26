package com.arnasRad.springcodeconfig;

public class BoxingCoach implements Coach {
    private FortuneService fortuneService;

    public BoxingCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Run 10 km and do 10 rounds for 3 minutes on a heavy bag";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
