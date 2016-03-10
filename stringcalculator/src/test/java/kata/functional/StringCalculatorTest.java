package kata.functional;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {
    @Test
    public void should_return_0_for_an_empty_string() {
        assertEquals(0, StringCalculator.sum(""));
    }

    @Test
    public void should_return_the_number_itself_for_one_number() {
        assertEquals(1, StringCalculator.sum("1"));
    }

    @Test
    public void should_return_the_sum_for_two_numbers() {
        assertEquals(3, StringCalculator.sum("1,2"));
    }

    @Test
    public void should_return_the_sum_for_unknown_amount_of_numbers() {
        assertEquals(10, StringCalculator.sum("1,2,3,4"));
    }

    @Test
    public void should_handle_new_lines_between_numbers() {
        assertEquals(10, StringCalculator.sum("1\n2,3,4"));
    }

    @Test
    public void should_support_different_delimiters() {
        assertEquals(3, StringCalculator.sum("//;\n1;2"));
    }

    @Test
    public void should_support_numbers_greater_than_10() {
        assertEquals(13, StringCalculator.sum("//;\n11;2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_exception_for_calling_add_with_a_negative_number() {
        StringCalculator.sum("//;\n11;-2");
    }

}
