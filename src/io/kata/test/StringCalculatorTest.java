package io.kata.test;

import io.kata.exception.InvalidInputException;
import io.kata.exception.NegativeNumbersException;
import io.kata.main.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    @Test
    public void testStringCalculatorAddDefaultDelimiter() {
        String arg =";\n1;2;3;6";
        assertEquals(Main.add(arg), 12);
    }

    @Test
    public void testStringCalculatorAddPassedDelimiter() {
        String arg ="1;2;3;6";
        assertEquals(Main.add(arg), 12);
    }

    @Test
    public void testStringCalculatorHandleNegativeNumbers() {
        String arg ="1;2;3;-6";
        assertThrows(NegativeNumbersException.class, () -> Main.add(arg));
    }

    @Test
    public void testStringCalculatorHandleNoNumbersAfterDelimiter() {
        String arg ="1;2;3;\n";
        assertEquals(Main.add(arg), 0);
    }

    @Test
    public void testStringCalculatorHandleInvalidInput() {
        String arg ="1;2;3;d";
        assertThrows(InvalidInputException.class, ()-> Main.add(arg));
    }
}
