package io.kata.helper;

import io.kata.exception.InvalidInputException;
import io.kata.exception.NegativeNumbersException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {

    /**
     *
     * @param arg A string containing in the first line a delimiter (optional),
     *            followed on the next line a ; seperated string or the delimiter
     *            specified in the first line.
     * <blockquote>For example,
     * <pre>{@code
     *     int num = Main.add(";\n1;2;3;6");
     *     // value returned is 12"
     * }</pre></blockquote>
     * @return An integer representing the sum of all the numbers passed in the argument
     * @throws NegativeNumbersException When the numbers list contains negative numbers
     * @throws NumberFormatException When the numbers list contains an invalid value

     * @since The dawn of time
     */
    public static int add(String arg) {

        // default values
        int result = 0;
        String delimiter = ";";
        String numbersLine = arg;

        // return 0 when argument is empty
        if (arg == null || arg.isEmpty()) {
            return result;
        }

        // check if the delimiter is passed in the argument
        if (arg.contains("\n")) {
            String[] lines = arg.split("\n");
            delimiter = lines[0];
            if (!arg.equals(delimiter+"\n")) {
                numbersLine = lines[1];
            } else {
                return 0;
            }
        }
        try {
            // Get a list of all the number in the argument
            List<Integer> numbers = Arrays.
                    stream(numbersLine.split(delimiter)).
                    map(Integer::parseInt).collect(Collectors.toList());
            // Negative numbers handling
            List<String> negativeNumbers = StringUtil.getNegativeNumber(numbers);
            if (!negativeNumbers.isEmpty()) {
                throw new NegativeNumbersException(String.format("Negatives not allowed %s",
                        String.join(", ", negativeNumbers)));
            }
            result = numbers.stream().reduce(Integer::sum).orElse(0);
        } catch (NumberFormatException ex) {
            throw new InvalidInputException("KO input");
        }
        return result;
    }

    private static List<String> getNegativeNumber(List<Integer> numbersList) {
        return numbersList.stream()
                .filter(nb -> nb<0)
                .map(String::valueOf).collect(Collectors.toList());
    }

}
