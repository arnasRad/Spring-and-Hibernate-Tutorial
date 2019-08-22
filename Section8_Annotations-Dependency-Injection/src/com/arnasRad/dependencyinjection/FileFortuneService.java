package com.arnasRad.dependencyinjection;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

@Component
public class FileFortuneService implements FortuneService {
    // create a random number generator
    private Random myRandom = new Random();
    // create a String array
    private String[] data;

    // create constructor that gets values from file and put them into data array
    public FileFortuneService() {
        try (BufferedReader br = new BufferedReader(new FileReader("src\\fortunes.txt"))) {

            ArrayList<String> dataList = new ArrayList<>();
            String str;
            while ((str = br.readLine()) != null) {
                dataList.add(str);
            }

            data = new String[dataList.size()];
            data = dataList.toArray(data);
        } catch (IOException e) {
            System.err.println("FileFortuneService: error reading fortunes file");
            e.printStackTrace();
        }
    }

    // get a random fortune from data array
    @Override
    public String getFortune() {
        if (data == null) {
            return null;
        }

        return data[myRandom.nextInt(data.length)];
    }
}
