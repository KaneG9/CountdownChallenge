package com.company;

import java.util.Random;

public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) {
        for(int i=1; i<10;i++) {
            System.out.println(generate(3));
        }

    }

    public static String generate(int numberOfLarge) {
        int pick = random.nextInt(100);
        if (pick < 15) {
            // no pairs
            return GeneratorV2.generate(numberOfLarge);
        } else if (pick < 60) {
            // 2 pairs
            return Generator.generate(numberOfLarge);
        } else {
            // 1 pair 1 triple
            return GeneratorV3.generate(numberOfLarge);
        }
    }
}
