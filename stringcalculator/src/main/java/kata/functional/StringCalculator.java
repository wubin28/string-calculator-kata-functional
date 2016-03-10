package kata.functional;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by twer on 3/10/16.
 */
public class StringCalculator {
    public static int sum(String input) {
        FluentIterable<Integer> numbers = FluentIterable.from(Arrays.asList(input.split("")))
                .filter(
                    new Predicate<String>() {
                        public boolean apply(String s) {
                            return !s.equals(",");
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
