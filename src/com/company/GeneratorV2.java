package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneratorV2 {
//    private static final List<Integer> LARGE_NUMBERS = new ArrayList<>(Arrays.asList(25,50,75,100));
    private static final Random random = new Random();
//    private static final List<Integer> SMALL_NUMBERS = new ArrayList<>(Arrays.asList(21,2,3,4,5,6,7,8,9,10));
// Approx 650ms for 100000
    public static void main(String[] args) {
//        long startTime = System.currentTimeMillis();
//        for(int i=0; i<100000; i++) {
//            GeneratorV2.generate(3);
//        }
//        long endTime = System.currentTimeMillis();
//        System.out.println("without get: " + (endTime-startTime));
        System.out.println(GeneratorV2.generate(3));
    }

    public static String generate(int numberOfLarge) {
        long startTime = System.currentTimeMillis();
        List<Integer> numbers = selectNumbers(numberOfLarge);

        while (true) {
            int total = numbers.get(0);
            int counter = 1;
            List<String> operators = new ArrayList<>();
            while(counter < 6) {
                int num = numbers.get(counter);
                switch(random.nextInt(4)) {
                    case 0:
                        operators.add("+");
                        total += num;
                        counter++;
                        break;
                    case 1:
                        operators.add("-");
                        total -= num;
                        counter++;
                        break;
                    case 2:
                        operators.add("*");
                        total *= num;
                        counter++;
                        break;
                    case 3:
                        if(total % num == 0) {
                            operators.add("/");
                            total /= num;
                            counter++;
                        }
                        break;
                }
            }
            if(total >= 100 && total < 1000) {
                long endTime = System.currentTimeMillis();
                long duration = endTime-startTime;
                return "Numbers: " + numbers + "\nTotal: " + total + "\nOperators: " + operators + "\nDuration: " + duration + "ms";
            }
        }
    }

    private static List<Integer> selectNumbers(int numberOfLarge) {
        List<Integer> LARGE_NUMBERS = new ArrayList<>(Arrays.asList(25,50,75,100));
        Collections.shuffle(LARGE_NUMBERS);
        List<Integer> numbers = LARGE_NUMBERS.subList(0, numberOfLarge);

        for(int i=0; i<(6-numberOfLarge); i++) {
            numbers.add(random.nextInt(10) + 1);
        }
        Collections.shuffle(numbers);
        return numbers;
    }
}
