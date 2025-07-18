import java.util.Scanner;
import java.util.Stack;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class RPNCalculator {

    /**
     * Rounds a double value to a specified number of decimal places.
     * This helps address floating point precision issues.
     *
     * @param value The double value to round.
     * @param places The number of decimal places to round to.
     * @return The rounded double value.
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Decimal places must be >= 0");

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Processes a single RPN expression and updates the provided stack.
     *
     * @param expression RPN expression input by the user.
     * @param stack Stack to maintain computation state.
     * @return The result of the computation (top of the stack after processing).
     */
    public static double processRPNExpression(String expression, Stack<Double> stack) {
        // Handle empty expression case
        if (expression.trim().isEmpty()) {
            throw new NumberFormatException("empty String");
        }

        String[] tokens = expression.trim().split("\\s+");

        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Insufficient values in the stack for operation.");
                }

                double b = stack.pop();
                double a = stack.pop();

                double result;
                switch (token) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        if (b == 0) {
                            throw new ArithmeticException("Division by zero is not allowed.");
                        }
                        result = a / b;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + token);
                }

                // Round the result to 10 decimal places to handle floating point precision issues
                stack.push(round(result, 10));
            } else {
                try {
                    // Interpret token as a number and push it onto the stack
                    stack.push(Double.parseDouble(token));
                } catch (NumberFormatException e) {
                    // If it's not a valid number, it's an invalid operator
                    throw new IllegalArgumentException("Invalid operator: " + token);
                }
            }
        }

        // Return the top value of the stack (result of the computation)
        if (!stack.isEmpty()) {
            return stack.peek();
        } else {
            throw new IllegalStateException("Stack is empty after evaluation.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the RPN Calculator!");
        System.out.println("Enter an RPN expression to evaluate or type 'exit' to quit.");

        Scanner scanner = new Scanner(System.in);
        Stack<Double> stack = new Stack<>();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the RPN Calculator. Goodbye!");
                break;
            }

            try {
                double result = processRPNExpression(input, stack);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                // Clear the stack to avoid propagating errors in future evaluations
                stack.clear();
            }
        }

        scanner.close();
    }
}
