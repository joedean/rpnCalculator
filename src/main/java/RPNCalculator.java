import java.util.Scanner;
import java.util.Stack;

public class RPNCalculator {

    /**
     * Processes a single RPN expression and updates the provided stack.
     *
     * @param expression RPN expression input by the user.
     * @param stack Stack to maintain computation state.
     * @return The result of the computation (top of the stack after processing).
     */
    public static double processRPNExpression(String expression, Stack<Double> stack) {
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

                stack.push(result);
            } else {
                // Interpret token as a number and push it onto the stack
                stack.push(Double.parseDouble(token));
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
