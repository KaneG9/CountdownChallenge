package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BaseGenerator {

    public static final Random random = new Random();
    protected static List<Integer> operators;
    protected static List<Integer> numbers;

    protected static String getOperatorSymbol(int operator) {
        switch (operator) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "*";
            case 3:
                return "/";
        }
        return "error";
    }

    protected static List<Integer> selectNumbers(int numberOfLarge) {
        List<Integer> LARGE_NUMBERS = new ArrayList<>(Arrays.asList(25,50,75,100));
        Collections.shuffle(LARGE_NUMBERS);
        List<Integer> numbers = LARGE_NUMBERS.subList(0, numberOfLarge);

        for(int i=0; i<(6-numberOfLarge); i++) {
            numbers.add(random.nextInt(10) + 1);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    protected static int getPair(int pair, int number1, int number2, int operator) {
        int result;
        if (operator == 1) {
            int ans;
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
}
