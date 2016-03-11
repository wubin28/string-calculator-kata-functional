package kata.functional;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkState;

/**
 * Created by twer on 3/11/16.
 */
public class StringParser {
    static FluentIterable<Integer> getIntegers(String input) {
        final String delimiter;
        final String formula;

        if (input.startsWith(StringCalculator.DELIMITER_SYMBOL)) {
            delimiter = input.substring(StringCalculator.DELIMITER_SYMBOL.length(), StringCalculator.DELIMITER_SYMBOL.length() + 1);
            formula = input.substring((StringCalculator.DELIMITER_SYMBOL + ".\n").length());
        } else {
            delimiter = StringCalculator.DEFAULT_DELIMITER;
            formula = input;
        }

        final String delimiterOnlyFormula = replaceNewLineWithDelimiter(delimiter, formula);

        checkState(
                !Iterables.contains(Arrays.asList(delimiterOnlyFormula.split("")), "-"),
                "negatives not allowed: " + Joiner.on(", ").join(
                        FluentIterable.from(Splitter.on(delimiter).split(delimiterOnlyFormula))
                                .filter(
                                        new Predicate<String>() {
                                            public boolean apply(String s) {
                                                return !s.equals(delimiter) && !s.equals("\n") && !s.equals("") && s.startsWith("-");
                                            }
                                        }
                                )
                )
        );

        return FluentIterable.from(Splitter.on(delimiter).split(delimiterOnlyFormula))
                .filter(
                        new Predicate<String>() {
                            public boolean apply(String s) {
                                return !s.equals(delimiter) && !s.equals("\n") && !s.equals("");
                            }
                        }
                )
                .transform(new Function<String, Integer>() {
                    public Integer apply(String s) {
                        return Integer.parseInt(s);
                    }
                });
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
