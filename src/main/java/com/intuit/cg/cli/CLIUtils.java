package com.intuit.cg.cli;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author sakhim
 */
public class CLIUtils {

    private static final Scanner SCANNER = new Scanner(System.in);

    private CLIUtils() {

    }

    public static <T> T parse(Predicate<T> invalidityCondition, Function<String, T> parser, T quitValue) {
        T parsedInput;
        do {
            printPromptChar();
            String input = SCANNER.nextLine().trim();
            if (isQuit(input)) {
                printFooter();
                return quitValue;
            }
            parsedInput = parser.apply(input);
        } while (invalidityCondition.test(parsedInput));
        return parsedInput;
    }

    public static int parseInteger(Function<String, Integer> parser) {
        return parse(i -> i == -1, parser, -1);
    }

    public static String parseString(Function<String, String> parser) {
        return parse(s -> s == null, parser, null);
    }

    public static void printHeader(String content) {
        System.out.println(">>> " + content + " (press 'Q' to quit)");
    }

    private static boolean isQuit(String input) {
        return input.equals("Q");
    }

    private static void printPromptChar() {
        System.out.print("> ");
    }

    private static void printFooter() {
        System.out.println(">>> Hope to see you soon");
    }

    // TODO: Refactor to command line validator class
    public static int validateInteger(String input, int min, int max) {
        try {
            int value = Integer.valueOf(input);
            if (value >= min && value <= max) {
                return value;
            }
        } catch (NumberFormatException ex) {
            // Ignore caught exception
        }
        System.out.println("An integer value between '" + min + "' and '" + max + "' is expected");
        return -1;
    }

    // TODO: Refactor to command line validator class
    public static String validatePlayerName(String input) {
        String regex = "^[A-Za-z0-9]{2,20}$";
        if (input.matches(regex)) {
            return input;
        }
        // TODO: Handle case when min equals max
        System.out.println("Player name '" + input + "' does not match '" + regex + "'");
        return null;
    }

}
