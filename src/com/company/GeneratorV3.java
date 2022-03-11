package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// Generate a triple and pair
public class GeneratorV3 extends BaseGenerator{
    private static final Random random = new Random();
    // Approx 1900ms for 100000
    public static void main(String[] args) {
//        long startTime = System.currentTimeMillis();
//        for(int i=0; i<100000; i++) {
//            GeneratorV3.generate(3);
//        }
//        long endTime = System.currentTimeMillis();
//        System.out.println("without get: " + (endTime-startTime));
        System.out.println(GeneratorV3.generate(3));
    }

    public static String generate(int numberOfLarge) {
        long startTime = System.currentTimeMillis();
        while (true) {
            numbers = selectNumbers(numberOfLarge);
            for(int j=0;j<10;j++) {
                operators = new ArrayList<>();
                // select operators
                for (int i = 0; i < 5; i++) {
                    if(i != 1) {
                        operators.add(random.nextInt(2));
                    } else {
                        operators.add(random.nextInt(2)+2);
                    }
                }

                int pair = getPair(0, numbers.get(0), numbers.get(1), operators.get(0));
                int triple = getTriple(numbers.get(2), numbers.get(3), numbers.get(4), operators.get(2), operators.get(3));


                List<Integer> pairedNumbers = Arrays.asList(pair, triple, numbers.get(5));
                List<Integer> pairedOperators = Arrays.asList(operators.get(1), operators.get(4));
                int total = pair;

                for(int counter = 1; counter < 3;) {
                    int num = pairedNumbers.get(counter);
                    int operatorSwitch = pairedOperators.get(counter - 1);
                    switch(operatorSwitch) {
                        case 0:
                            total += num;
                            counter++;
                            break;
                        case 1:
                            total -= num;
                            counter++;
                            break;
                        case 2:
                            total *= num;
                            counter++;
                            break;
                        case 3:
                            if(total % num == 0) {
                                total /= num;
                            } else {
                                total *= num;
                                operators.set(1, 2);
                            }
                            counter++;
                            break;
                    }
                }
                if(total >= 100 && total < 1000) {
                    long endTime = System.currentTimeMillis();
                    long duration = endTime-startTime;
                    return "Numbers: " + numbers + "\nTotal: " + total + "\nOperators: " + operators + "\nDuration: " + duration + "ms" + "\nSolution: " + buildSolution();
                }
            }
        }
    }

    private static int getTriple(int number1, int number2, int number3, int operator1, int operator2) {
        int calcPair = getPair(2, number1, number2, operator1);
        return getPair(3, calcPair, number3, operator2);
    }

    private static String buildSolution() {
        StringBuilder result = new StringBuilder("(" + numbers.get(0).toString());
        for (int i=0; i<5; i++) {
            result.append(getOperatorSymbol(operators.get(i)));
            if (i == 1) {
                result.append("(");
            }
            result.append(numbers.get(i + 1).toString());
            if (i == 0 || i == 3) {
                result.append(")");
            }
        }

        return result.toString();
    }
}
