package kata.functional;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {
    @Test
    public void should_return_0_for_an_empty_string() {
        assertEquals(0, StringCalculator.sum(""));
    }

    // TODO: should_return_the_number_itself_for_one_number
    // TODO: should_return_the_sum_for_two_numbers

}
