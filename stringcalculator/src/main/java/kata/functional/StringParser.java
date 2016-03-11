package kata.functional;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkState;

/**
 * Created by twer on 3/11/16.
 */
public class StringParser {
    public static final String DELIMITER_SYMBOL = "//";
    public static final String DEFAULT_DELIMITER = ",";

    static FluentIterable<Integer> getIntegers(String input, Predicate<String> validNumber) {
        final String delimiter;
        final String formula;

        if (input.startsWith(DELIMITER_SYMBOL)) {
            delimiter = input.substring(DELIMITER_SYMBOL.length(), DELIMITER_SYMBOL.length() + 1);
            formula = input.substring((DELIMITER_SYMBOL + ".\n").length());
        } else {
            delimiter = DEFAULT_DELIMITER;
            formula = input;
        }

        final String delimiterOnlyFormula = replaceNewLineWithDelimiter(delimiter, formula);

        checkState(
                !delimiterOnlyFormula.contains("-"),
                "negatives not allowed: " + getAllNegativeNumbers(delimiter, delimiterOnlyFormula)
        );

        return transformStringsToIntegers(delimiter, delimiterOnlyFormula, validNumber);
    }

    private static FluentIterable<Integer> transformStringsToIntegers(final String delimiter, String delimiterOnlyFormula, Predicate<String> validNumber) {
        return FluentIterable.from(Splitter.on(delimiter).split(delimiterOnlyFormula))
                .filter(validNumber)
                .transform(new Function<String, Integer>() {
                    public Integer apply(String s) {
                        return Integer.parseInt(s);
                    }
                });
    }

    private static String getAllNegativeNumbers(final String delimiter, String delimiterOnlyFormula) {
        return Joiner.on(", ").join(
                FluentIterable.from(Splitter.on(delimiter).split(delimiterOnlyFormula))
                        .filter(
                                new Predicate<String>() {
                                    public boolean apply(String s) {
                                        return !s.equals(delimiter) && !s.equals("\n") && !s.equals("") && s.startsWith("-");
                                    }
                                }
                        )
        );
    }

    private static String replaceNewLineWithDelimiter(final String delimiter, String formula) {
        return Joiner.on("").join(FluentIterable.from(Arrays.asList(formula.split("")))
                .transform(new Function<String, String>() {
                    public String apply(String s) {
                        return s.equals("\n") ? delimiter : s;
                    }
                }));
    }
}
