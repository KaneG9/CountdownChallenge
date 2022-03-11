package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratorV2 extends BaseGenerator {
    private static final Random random = new Random();
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
        numbers = selectNumbers(numberOfLarge);

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
                StringBuilder result = new StringBuilder(numbers.get(0).toString());
                for(int i=0; i<5; i++) {
                    result.append(operators.get(i)).append(numbers.get(i + 1).toString());
                }
                return "Numbers: " + numbers + "\nTotal: " + total + "\nOperators: " + operators + "\nDuration: " + duration + "ms" + "\nSolution: " + result;
            }
        }
    }
}
