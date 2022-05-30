package io.kata.test;

import io.kata.exception.InvalidInputException;
import io.kata.exception.NegativeNumbersException;
import io.kata.helper.StringUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringUtilTest {

    @Test
    public void testStringCalculatorAddDefaultDelimiter() {
        String arg =";\n1;2;3;6";
        assertEquals(StringUtil.add(arg), 12);
    }

    @Test
    public void testStringCalculatorAddPassedDelimiter() {
        String arg ="1;2;3;6";
        assertEquals(StringUtil.add(arg), 12);
    }

    @Test
    public void testStringCalculatorHandleNegativeNumbers() {
        String arg ="1;2;3;-6";
        assertThrows(NegativeNumbersException.class, () -> StringUtil.add(arg));
    }

    @Test
    public void testStringCalculatorHandleNoNumbersAfterDelimiter() {
        String arg ="1;2;3;\n";
        assertEquals(StringUtil.add(arg), 0);
    }

    @Test
    public void testStringCalculatorHandleInvalidInput() {
        String arg ="1;2;3;d";
        assertThrows(InvalidInputException.class, ()-> StringUtil.add(arg));
    }

    @Test
    public void testStringCalculatorHandleNullInput() {
        String arg = null;
        assertEquals(StringUtil.add(arg), 0);
    }

    @Test
    public void testStringCalculatorHandleEmptyInput() {
        String arg = "";
        assertEquals(StringUtil.add(arg), 0);
    }
}
