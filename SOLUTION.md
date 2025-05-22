# RPN Calculator Decimal Addition Issue

## Issue Description
The RPN Calculator was incorrectly adding extra decimal points when calculating decimal additions. For example, when adding 0.1 and 0.2, the result was displayed as 0.30000000000000004 instead of 0.3.

## Root Cause
The issue was caused by floating-point precision errors in Java's double data type. Due to the way floating-point numbers are represented in binary, some decimal numbers cannot be represented exactly, leading to small rounding errors. For example, 0.1 and 0.2 cannot be represented exactly in binary floating-point, so when they're added, the result is slightly off from the expected 0.3.

## Solution
To fix this issue, we implemented a rounding mechanism using Java's BigDecimal class, which provides precise decimal arithmetic. We added a `round()` method that rounds a double value to a specified number of decimal places, and modified the `processRPNExpression()` method to round the result of each operation to 10 decimal places.

### Changes Made:
1. Added imports for BigDecimal and RoundingMode
2. Added a `round()` method to handle decimal rounding
3. Modified the `processRPNExpression()` method to round the result of each operation
4. Updated error handling to maintain compatibility with existing tests

### Code Changes:
```java
// Added imports
import java.math.BigDecimal;
import java.math.RoundingMode;

// Added rounding method
private static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException("Decimal places must be >= 0");
    
    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
}

// Modified operations to use rounding
case "+":
    result = round(a + b, 10); // Round to 10 decimal places
    break;
// Similar changes for other operations
```

## Testing
We created comprehensive test cases to verify the fix:
1. Simple decimal addition (5.5 + 3.3 = 8.8)
2. Multiple decimal operations (5.5 + 3.3 + 2.2 = 11.0)
3. Complex sequence (1.1 + 2.2 + 3.3 + 4.4 = 11.0)
4. Decimal with many digits (1.23456789 + 2.98765432 = 4.22222221)
5. Repeated decimal operations (0.1 + 0.2 = 0.3, 0.3 + 0.3 = 0.6, etc.)

All tests now pass, and the calculator correctly handles decimal additions without adding extra decimal points.
