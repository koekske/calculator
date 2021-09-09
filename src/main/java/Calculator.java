package main.java;

import java.util.ArrayList;

public class Calculator {

    public int sumOf(String numbers) {
        int result = 0;
        if (numbers.equals("")){
            return result;
        }
        String[] arrOfStrings = split((numbers));
        ArrayList<Integer> intList = parse(arrOfStrings);
        intList = checkOnNegatives(intList);
        for (int number : intList) {
            if (number<=1000) {
                    result += number;
            }
        }
        return result;
    }

    public String[] split(String numbersString) {
        String[] arrOfString = numbersString.split(",|\\;|\\||\\\n|/" , 0);
        return arrOfString;
    }

    public ArrayList<Integer> parse(String[] arrOfStrings) {
        ArrayList<Integer> listOfInts = new ArrayList<Integer>();
        for (String number : arrOfStrings) {
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
