package kata.functional;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkState;

/**
 * Created by twer on 3/10/16.
 */
public class StringCalculator {

    public static final String DEFAULT_DELIMITER = ",";
    public static final String DELIMITER_SYMBOL = "//";

    public static int sum(String input) {

        final String delimiter;
        final String formula;

        if (input.startsWith(DELIMITER_SYMBOL)) {
            delimiter = input.substring(DELIMITER_SYMBOL.length(), DELIMITER_SYMBOL.length() + 1);
            formula = input.substring((DELIMITER_SYMBOL + ".\n").length());
        } else {
            delimiter = DEFAULT_DELIMITER;
            formula = input;
        }

        final String delimiterOnlyFormula = Joiner.on("").join(FluentIterable.from(Arrays.asList(formula.split("")))
                .transform(new Function<String, String>() {
                    public String apply(String s) {
                        return s.equals("\n") ? delimiter : s;
                    }
                }));

        FluentIterable<Integer> numbers = FluentIterable.from(Splitter.on(delimiter).split(delimiterOnlyFormula))
                .filter(
                    new Predicate<String>() {
                        public boolean apply(String s) {
                            return !s.equals(delimiter) && !s.equals("\n") && !s.equals("");
                        }
                    }
                )
                .transform(new Function<String, Integer>() {
                    public Integer apply(String s) {
                        checkState(Integer.parseInt(s) >= 0, "negatives not allowed: " + s);
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
