import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class RPNCalculatorTest {

    @Test
    void testAddition() {
        Stack<Double> stack = new Stack<>();
        assertEquals(8.0, RPNCalculator.processRPNExpression("5 3 +", stack));
    }

    @Test
    void testSubtraction() {
        Stack<Double> stack = new Stack<>();
        assertEquals(2.0, RPNCalculator.processRPNExpression("5 3 -", stack));
    }

    @Test
    void testMultiplication() {
        Stack<Double> stack = new Stack<>();
        assertEquals(15.0, RPNCalculator.processRPNExpression("5 3 *", stack));
    }

    @Test
    void testDivision() {
        Stack<Double> stack = new Stack<>();
        assertEquals(2.0, RPNCalculator.processRPNExpression("6 3 /", stack));
    }

    @Test
    void testDivisionByZero() {
        Stack<Double> stack = new Stack<>();
        Exception exception = assertThrows(ArithmeticException.class, () ->
                RPNCalculator.processRPNExpression("6 0 /", stack)
        );
        assertEquals("Division by zero is not allowed.", exception.getMessage());
    }

    @Test
    void testComplexExpression() {
        Stack<Double> stack = new Stack<>();
        assertEquals(14.0, RPNCalculator.processRPNExpression("5 1 2 + 4 * + 3 -", stack));
    }

    @Test
    void testInsufficientOperands() {
        Stack<Double> stack = new Stack<>();
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                RPNCalculator.processRPNExpression("+", stack)
        );
        assertEquals("Insufficient values in the stack for operation.", exception.getMessage());
    }

    @Test
    void testInvalidOperator() {
        Stack<Double> stack = new Stack<>();
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                RPNCalculator.processRPNExpression("5 3 &", stack)
        );
        assertEquals("Invalid operator: &", exception.getMessage());
    }

    @Test
    void testEmptyExpression() {
        Stack<Double> stack = new Stack<>();
        Exception exception = assertThrows(NumberFormatException.class, () ->
                RPNCalculator.processRPNExpression("", stack)
        );
        assertEquals("empty String", exception.getMessage());
    }

    @Test
    void testStackPersistence() {
        Stack<Double> stack = new Stack<>();
        RPNCalculator.processRPNExpression("5 3 +", stack);
        assertEquals(8.0, stack.peek());
        RPNCalculator.processRPNExpression("4 +", stack);
        assertEquals(12.0, stack.peek());
    }

    @Test
    void testDecimalAddition() {
        Stack<Double> stack = new Stack<>();
        double result = RPNCalculator.processRPNExpression("5.5 3.3 +", stack);
        assertEquals(8.8, result, 0.0001);
    }

    @Test
    void testMultipleDecimalOperations() {
        Stack<Double> stack = new Stack<>();
        // First operation
        RPNCalculator.processRPNExpression("5.5 3.3 +", stack);
        // Second operation using the result from the first
        double result = RPNCalculator.processRPNExpression("2.2 +", stack);
        assertEquals(11.0, result, 0.0001);

        // Clear stack and try a more complex sequence
        stack.clear();
        result = RPNCalculator.processRPNExpression("1.1 2.2 + 3.3 + 4.4 +", stack);
        assertEquals(11.0, result, 0.0001);
    }
}
