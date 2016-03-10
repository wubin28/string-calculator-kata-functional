package kata.functional;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

import java.util.Arrays;

/**
 * Created by twer on 3/10/16.
 */
public class StringCalculator {

    public static final String DEFAULT_DELIMITER = ",";
    public static final String DELIMITER_SYMBOL = "//";

    public static int sum(String input) {

        final String delimiter;
        String formula = input;

        if (input.startsWith(DELIMITER_SYMBOL)) {
            delimiter = input.substring(DELIMITER_SYMBOL.length(), DELIMITER_SYMBOL.length() + 1);
            formula = input.substring((DELIMITER_SYMBOL + ".\n").length());
        } else {
            delimiter = DEFAULT_DELIMITER;
        }

        System.out.println("formula: " + formula);

        FluentIterable<Integer> numbers = FluentIterable.from(Arrays.asList(formula.split("")))
                .filter(
                    new Predicate<String>() {
                        public boolean apply(String s) {
                            return !s.equals(delimiter);
                        }
                    }
                )
                .filter(
                        new Predicate<String>() {
                            public boolean apply(String s) {
                                return !s.equals("\n");
                            }
                        }
                )
                .filter(
                        new Predicate<String>() {
                            public boolean apply(String s) {
                                return !s.equals("");
                            }
                        }
                )
                .transform(new Function<String, Integer>() {
                    public Integer apply(String s) {
                        return Integer.parseInt(s);
                    }
                });

        int sum = 0;

        for (Integer number : numbers) {
            sum += number;
        }

        return sum;
    }
}
