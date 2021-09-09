package test;

import main.java.Calculator;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;


public class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Optellen negeer boven 1000")
    void testSumOf() {
        try {
            assertEquals(0, calculator.sumOf(""));
            assertEquals(36, calculator.sumOf("1,2,3,4,5,6,7,8"));
            assertEquals(7, calculator.sumOf("1,2/4"));
            assertNotEquals(5, calculator.sumOf("1|2;1001\n4/5"));
            assertEquals(117, calculator.sumOf("1;2|50\n53/5,6"));
            assertEquals(5, calculator.sumOf("1,-2/4"));
            assertEquals(1014, calculator.sumOf("1;2|1000\n1001/5,6"));
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is ("negatieve cijfers zijn niet toegestaan"));
        }
    }

    @Test
    @DisplayName("split en parse")
    void testOfSplitAndParse() {
        ArrayList<Integer> list = calculator.splitAndParse("1;2|1000\n1001/5,6");
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(1000, list.get(2));
        assertEquals(1001, list.get(3));
        assertEquals(5, list.get(4));
        assertEquals(6, list.get(5));
    }

}
