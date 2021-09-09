package test;

import main.java.Calculator;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;


public class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Optellen tot 2 getallen")
    void testSumOf2Numbers() {
        assertEquals(0, calculator.sumOf(""));
        assertEquals(3, calculator.sumOf("1,2"));
        assertNotEquals(5, calculator.sumOf("1,2"));
    }

    @Test
    @DisplayName("Optellen geen limit")
    void testSumOfNoLimit() {
        assertEquals(21, calculator.sumOf("1,2,3,4,5,6"));
        assertEquals(64, calculator.sumOf("1,2,10,16,17,18"));
        assertNotEquals(5, calculator.sumOf("1,2,3,4,5"));
    }

    @Test
    @DisplayName("Optellen 2 seperators")
    void testSumOfSeperator() {
        assertEquals(21, calculator.sumOf("1,2,3/4,5/6"));
        assertEquals(11, calculator.sumOf("1/2,8"));
        assertNotEquals(5, calculator.sumOf("1,2/3,4/5"));
    }

    @Test
    @DisplayName("Optellen meerdere seperators")
    void testSumOfMultipleSeparator() {
        assertEquals(21, calculator.sumOf("1;2|3\n4/5,6"));
        assertEquals(18, calculator.sumOf("1;2\n4/5,6"));
        assertNotEquals(5, calculator.sumOf("1|2;3\n4/5"));
    }

    @Test
    @DisplayName("Optellen negatief")
    void testSumOfNegative() {
        try {
            assertNotEquals(5, calculator.sumOf("1|2;-3\n4/5"));
            assertEquals(18, calculator.sumOf("1;2|-3\n4/5,6"));
        } catch (IllegalArgumentException e) {
            MatcherAssert.assertThat(e.getMessage(), is ("negatieve cijfers zijn niet toegestaan"));
        }

    }

    @Test
    @DisplayName("Optellen negeer boven 1000")
    void testSumOfThousand() {
            assertNotEquals(5, calculator.sumOf("1|2;1001\n4/5"));
            assertEquals(1014, calculator.sumOf("1;2|1000\n1001/5,6"));
    }

    @Test
    @DisplayName("test split en parse")
    void testOfSplitAndParse() {
        ArrayList<Integer> list = calculator.splitAndParse("1;2|1000\n1001/5,6");
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(1000, list.get(2));
        assertEquals(1001, list.get(3));
        assertEquals(5, list.get(4));
        assertEquals(6, list.get(5));
    }

    @Test
    @DisplayName("test check op negatives")
    void testNegative() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(-2);
        list.add(1000);
        list.add(-1001);
        list.add(5);
        try {
            calculator.checkOnNegatives(list);
        } catch (IllegalArgumentException e) {
            MatcherAssert.assertThat(e.getMessage(), is("negatieve cijfers zijn niet toegestaan"));
        }
    }

}
