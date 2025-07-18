import java.util.Stack;

public class FloatingPointTest {
    public static void main(String[] args) {
        // Test the floating point issue directly
        System.out.println("Direct addition: 0.1 + 0.2 = " + (0.1 + 0.2));

        // Test using the RPNCalculator
        Stack<Double> stack = new Stack<>();
        double result = RPNCalculator.processRPNExpression("0.1 0.2 +", stack);
        System.out.println("RPN Calculator: 0.1 + 0.2 = " + result);

        // Verify equality
        System.out.println("Is result exactly 0.3? " + (result == 0.3));

        // Test with the round method directly
        double roundedResult = RPNCalculator.round(0.1 + 0.2, 10);
        System.out.println("Rounded result: " + roundedResult);
        System.out.println("Is rounded result exactly 0.3? " + (roundedResult == 0.3));
    }
}
