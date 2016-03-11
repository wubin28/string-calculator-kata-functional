package kata.functional;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import static com.google.common.base.Preconditions.checkState;

/**
 * Created by twer on 3/10/16.
 */
public class StringCalculator {

    public static int sum(String input) {

        FluentIterable<Integer> numbers = StringParser.getIntegers(input, new Predicate<String>() {
            public boolean apply(String s) {
                return !s.equals("\n") && !s.equals("") && Integer.parseInt(s) <= 1000;
            }
        });

        int sum = 0;

        for (Integer number : numbers) {
            sum += number;
        }

        return sum;
    }

}
