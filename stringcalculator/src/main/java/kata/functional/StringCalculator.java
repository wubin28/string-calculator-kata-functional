package kata.functional;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import static com.google.common.base.Preconditions.checkState;

/**
 * Created by twer on 3/10/16.
 */
public class StringCalculator {

    public static int sum(String input, Predicate<String> validNumber) {

        FluentIterable<Integer> numbers = StringParser.getIntegers(input, validNumber);

        int sum = 0;

        for (Integer number : numbers) {
            sum += number;
        }

        return sum;
    }

}
