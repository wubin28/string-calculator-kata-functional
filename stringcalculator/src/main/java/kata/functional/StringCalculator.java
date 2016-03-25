package kata.functional;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

/**
 * Created by twer on 3/10/16.
 */
public class StringCalculator {

    public static int sum(String input, Predicate<String> validNumber) {

        FluentIterable<Integer> numbers = StringParser.extractIntegers(input, validNumber);

        int sum = 0;

        for (Integer number : numbers) {
            sum += number;
        }

        return sum;
    }

}
