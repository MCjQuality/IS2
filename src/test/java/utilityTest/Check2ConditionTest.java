package utilityTest;

import it.unibs.ing.elaborato.util.Utility;
import org.junit.Test;

import java.util.Scanner;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

public class Check2ConditionTest {

    @Test
    public void testCondition1True() {
        Scanner scanner = new Scanner("\nvalidInput\n");
        Predicate<String> condition1 = String::isEmpty;
        Predicate<String> condition2 = s -> s.matches("\\d+");

        String result = Utility.check2Condition("Enter value: ", "Error 1: Empty string!", "Error 2: Contains numbers!", condition1, condition2, scanner);

        assertEquals("validInput", result);
    }

    @Test
    public void testCondition2True() {
        Scanner scanner = new Scanner("123\nvalidInput\n");
        Predicate<String> condition1 = String::isEmpty;
        Predicate<String> condition2 = s -> s.matches("\\d+");

        String result = Utility.check2Condition("Enter value: ", "Error 1: Empty string!", "Error 2: Contains numbers!", condition1, condition2, scanner);

        assertEquals("validInput", result);
    }

    @Test
    public void testBothConditionsTrue() {
        Scanner scanner = new Scanner("validInput\n");
        Predicate<String> condition1 = String::isEmpty;
        Predicate<String> condition2 = s -> s.matches("\\d+");

        String result = Utility.check2Condition("Enter value: ", "Error 1: Empty string!", "Error 2: Contains numbers!", condition1, condition2, scanner);

        assertEquals("validInput", result);
    }
}