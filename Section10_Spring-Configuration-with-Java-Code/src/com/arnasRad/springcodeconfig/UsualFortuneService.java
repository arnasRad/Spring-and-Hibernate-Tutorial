package com.arnasRad.springcodeconfig;

public class UsualFortuneService implements FortuneService {
    @Override
    public String getFortune() {
        return "Today is an usual day regarding your luck";
    }
}
