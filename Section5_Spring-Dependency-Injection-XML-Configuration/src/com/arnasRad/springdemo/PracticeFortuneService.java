package com.arnasRad.springdemo;

import java.util.Random;

public class PracticeFortuneService implements FortuneService {
    private String[] fortunes = {
            "Fortune will smile upon you today!",
            "Everything's as usual today... You'll be neither lucky nor unlucky",
            "Caution! Luck will NOT be on your side today!"
    };

    private Random random = new Random();

    @Override
    public String getFortune() {
        return getRandomFortune();
    }

    private String getRandomFortune() {
        // get random int: Random.nextInt((max - min) + 1) + min;

        return fortunes[random.nextInt(fortunes.length)];
    }
}
