package main.java;

import java.util.ArrayList;

public class Calculator {

    public int sumOf(String numbers) {
        int result = 0;
        if (numbers.equals("")){
            return result;
        }
        for (int number : splitAndParse(numbers)) {
            if (number<=1000) {
                if (number<0) {
                    throw new IllegalArgumentException("negatieve cijfers zijn niet toegestaan");
                } else {
                    result += number;
                }
            }
        }
        return result;
    }

    public ArrayList<Integer> splitAndParse(String numbersString) {
        ArrayList<Integer> listOfInts = new ArrayList<Integer>();
        String[] arrOfString = numbersString.split(",|\\;|\\||\\\n|/" , 0);
        for (String number : arrOfString) {
            listOfInts.add(Integer.parseInt(number));
        }
        return listOfInts;
    }

}
