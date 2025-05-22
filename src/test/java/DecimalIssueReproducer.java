import java.util.Stack;

public class DecimalIssueReproducer {
    public static void main(String[] args) {
        Stack<Double> stack = new Stack<>();

        // Test case 1: Simple decimal addition
        System.out.println("Test case 1: Simple decimal addition");
        double result1 = RPNCalculator.processRPNExpression("5.5 3.3 +", stack);
        System.out.println("5.5 + 3.3 = " + result1);
        stack.clear();

        // Test case 2: Multiple decimal operations
        System.out.println("\nTest case 2: Multiple decimal operations");
        RPNCalculator.processRPNExpression("5.5 3.3 +", stack);
        double result2 = RPNCalculator.processRPNExpression("2.2 +", stack);
        System.out.println("5.5 + 3.3 + 2.2 = " + result2);
        stack.clear();

        // Test case 3: Complex sequence
        System.out.println("\nTest case 3: Complex sequence");
        double result3 = RPNCalculator.processRPNExpression("1.1 2.2 + 3.3 + 4.4 +", stack);
        System.out.println("1.1 + 2.2 + 3.3 + 4.4 = " + result3);
        stack.clear();

        // Test case 4: Decimal with many digits
        System.out.println("\nTest case 4: Decimal with many digits");
        double result4 = RPNCalculator.processRPNExpression("1.23456789 2.98765432 +", stack);
        System.out.println("1.23456789 + 2.98765432 = " + result4);
        stack.clear();

        // Test case 5: Repeated decimal operations
        System.out.println("\nTest case 5: Repeated decimal operations");
        RPNCalculator.processRPNExpression("0.1 0.2 +", stack);
        System.out.println("After 0.1 + 0.2, stack top: " + stack.peek());
        RPNCalculator.processRPNExpression("0.3 +", stack);
        System.out.println("After adding 0.3, stack top: " + stack.peek());
        RPNCalculator.processRPNExpression("0.4 +", stack);
        System.out.println("After adding 0.4, stack top: " + stack.peek());
        RPNCalculator.processRPNExpression("0.5 +", stack);
        System.out.println("After adding 0.5, stack top: " + stack.peek());
    }
}
