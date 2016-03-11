package kata.functional;

import com.google.common.collect.FluentIterable;

import static com.google.common.base.Preconditions.checkState;

/**
 * Created by twer on 3/10/16.
 */
public class StringCalculator {

    public static final String DEFAULT_DELIMITER = ",";
    public static final String DELIMITER_SYMBOL = "//";

    public static int sum(String input) {

        FluentIterable<Integer> numbers = StringParser.getIntegers(input);

        int sum = 0;

        for (Integer number : numbers) {
            sum += number;
        }

        return sum;
    }

}
