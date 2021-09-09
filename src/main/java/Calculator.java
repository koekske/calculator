package main.java;

import java.util.ArrayList;

public class Calculator {

    public int sumOf(String numbers) {
        int result = 0;
        if (numbers.equals("")){
            return result;
        }
        ArrayList<Integer> intList = splitAndParse((numbers));
        intList = checkOnNegatives(intList);
        for (int number : intList) {
            if (number<=1000) {
                    result += number;
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

    public ArrayList<Integer> checkOnNegatives(ArrayList<Integer> list) {
        for(int i=0; i < list.size()-1;i++) {
            if (list.get(i)<0) {
                list.remove(i);
                throw new IllegalArgumentException("negatieve cijfers zijn niet toegestaan");
            }
        }
        return list;
    }

}
