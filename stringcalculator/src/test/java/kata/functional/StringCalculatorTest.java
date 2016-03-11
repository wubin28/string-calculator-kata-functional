package kata.functional;

import com.google.common.base.Predicate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {

    private Predicate<String> validNumber = new Predicate<String>() {
        public boolean apply(String s) {
            return !s.equals("\n") && !s.equals("") && Integer.parseInt(s) <= 1000;
        }
    };

    @Test
    public void should_return_0_for_an_empty_string() {
        assertEquals(0, StringCalculator.sum("", validNumber));
    }

    @Test
    public void should_return_the_number_itself_for_one_number() {
        assertEquals(1, StringCalculator.sum("1", validNumber));
    }

    @Test
    public void should_return_the_sum_for_two_numbers() {
        assertEquals(3, StringCalculator.sum("1,2", validNumber));
    }

    @Test
    public void should_return_the_sum_for_unknown_amount_of_numbers() {
        assertEquals(10, StringCalculator.sum("1,2,3,4", validNumber));
    }

    @Test
    public void should_handle_new_lines_between_numbers() {
        assertEquals(10, StringCalculator.sum("1\n2,3,4", validNumber));
    }

    @Test
    public void should_support_different_delimiters() {
        assertEquals(3, StringCalculator.sum("//;\n1;2", validNumber));
    }

    @Test
    public void should_support_numbers_greater_than_10() {
        assertEquals(13, StringCalculator.sum("//;\n11;2", validNumber));
    }

    @Test(expected = IllegalStateException.class)
    public void should_throw_an_exception_for_calling_add_with_a_negative_number() {
        try {
            StringCalculator.sum("//;\n11;-2", validNumber);
        } catch (IllegalStateException exception) {
            assertEquals("negatives not allowed: -2", exception.getMessage());
            throw exception;
        }
    }

    @Test(expected = IllegalStateException.class)
    public void should_show_all_negative_numbers_in_the_exception() {
        try {
            StringCalculator.sum("//;\n-11;-2", validNumber);
        } catch (IllegalStateException exception) {
            assertEquals("negatives not allowed: -11, -2", exception.getMessage());
            throw exception;
        }
    }

    @Test
    public void numbers_bigger_than_1000_should_be_ignored() {
        assertEquals(2, StringCalculator.sum("2,1001", validNumber));
    }
}
