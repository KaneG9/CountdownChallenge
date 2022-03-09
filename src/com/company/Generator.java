package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Generator {
//    private static final List<int> LARGE_NUMBERS = new ArrayList<>(Arrays.asList(25,50,75,100));
    private static final Random random = new Random();
    private static List<Integer> operators;
//    private static final List<int> SMALL_NUMBERS = new ArrayList<>(Arrays.asList(21,2,3,4,5,6,7,8,9,10));
// Approx 1900ms for 100000
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for(int i=0; i<100000; i++) {
            Generator.generate(3);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("without get: " + (endTime-startTime));
//        System.out.println(Generator.generate(3));
    }

    public static String generate(int numberOfLarge) {
        long startTime = System.currentTimeMillis();
        while (true) {
            List<Integer> numbers = selectNumbers(numberOfLarge);
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

                int pair1 = getPair(0, numbers.get(0), numbers.get(1), operators.get(0));
                int pair2 = getPair(2, numbers.get(2), numbers.get(3), operators.get(2));
                List<Integer> pairedNumbers = Arrays.asList(pair1, pair2, numbers.get(4), numbers.get(5));
                List<Integer> pairedOperators = new ArrayList<>(List.of(operators.get(1)));
                pairedOperators.addAll(operators.subList(3,5));
                int total = pair1;

                for(int counter = 1; counter < 4;) {
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
                    return "Numbers: " + numbers + "\nTotal: " + total + "\nOperators: " + operators + "\nDuration: " + duration + "ms";
                }
            }


        }
    }

    private static int getPair(int pair, int number1, int number2, int operator) {
        int result;
        if (operator == 1) {
            int ans;
            //todo check if assigning to ans faster than calcing twice
            if ((ans = number1 - number2) > 0) {
                result = ans;
            } else {
                result = number1 + number2;
                operators.set(pair, 0);
            }
        } else {
            result = number1 + number2;
        }
        return result;
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
