package com.arnasRad.beanscopes;

public class BaseballCoach implements com.arnasRad.beanscopes.Coach {
    // define a private field for the dependency
    private com.arnasRad.beanscopes.FortuneService fortuneService;

    // define a constructor for dependency injection
    public BaseballCoach(com.arnasRad.beanscopes.FortuneService theFortuneService) {
        fortuneService = theFortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes on batting practice";
    }

    @Override
    public String getDailyFortune() {
        // use my fortuneService to get a fortune
        return fortuneService.getFortune();
    }
}
