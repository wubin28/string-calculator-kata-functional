package kata.functional;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by twer on 3/10/16.
 */
public class StringCalculator {
    public static int sum(String input) {
        Iterator<String> numbers = FluentIterable.from(Arrays.asList(input.split("")))
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
                .iterator();
        System.out.println("Expression: " +
                Joiner.on("+").join(numbers));

        if (input.length() == 0) {
            return 0;
        }
        String[] strings = input.split(",|\n");
        int result = 0;
        for (String string : strings) {
            result += Integer.parseInt(string);
        }
        return result;
    }
}
