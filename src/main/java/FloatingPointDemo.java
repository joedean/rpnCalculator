import java.util.Stack;

public class FloatingPointDemo {
    public static void main(String[] args) {
        System.out.println("Floating Point Precision Demo");
        System.out.println("============================");

        // Demonstrate the floating point precision issue
        System.out.println("Standard floating point addition:");
        System.out.println("0.1 + 0.2 = " + (0.1 + 0.2));
        System.out.println("Is 0.1 + 0.2 == 0.3? " + ((0.1 + 0.2) == 0.3));
        System.out.println();

        // Demonstrate our solution using the RPNCalculator
        System.out.println("Using RPNCalculator with rounding:");
        Stack<Double> stack = new Stack<>();
        double result = RPNCalculator.processRPNExpression("0.1 0.2 +", stack);
        System.out.println("RPNCalculator(\"0.1 0.2 +\") = " + result);
        System.out.println("Is result == 0.3? " + (result == 0.3));
        System.out.println();

        // Demonstrate the round method directly
        System.out.println("Using round method directly:");
        double roundedResult = RPNCalculator.round(0.1 + 0.2, 10);
        System.out.println("round(0.1 + 0.2, 10) = " + roundedResult);
        System.out.println("Is rounded result == 0.3? " + (roundedResult == 0.3));
    }
}
