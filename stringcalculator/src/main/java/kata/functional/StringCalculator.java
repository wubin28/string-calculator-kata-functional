package kata.functional;

/**
 * Created by twer on 3/10/16.
 */
public class StringCalculator {
    public static int sum(String input) {
        if (input.length() == 0) {
            return 0;
        }
        String[] strings = input.split(",");
        int result = 0;
        for (String string : strings) {
            result += Integer.parseInt(string);
        }
        return result;
    }
}
