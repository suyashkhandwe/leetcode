# Problem 224. Basic Calculator

> [!NOTE]
> [224. Basic Calculator](https://leetcode.com/problems/basic-calculator/description/?envType=study-plan-v2&envId=top-interview-150)

Given a string `s` representing a valid expression, implement a basic calculator to evaluate it, and return _the result of the evaluation_.

**Note**: You are **not** allowed to use any built-in function which evaluates strings as mathematical expressions, such as `eval()`.

### Examples

#### Example 1:

> **Input**: `s = "1 + 1"`<br/>
> **Output**: `2`

#### Example 2:

> **Input**: `s = " 2-1 + 2 "`<br/>
> **Output**: `3`

#### Example 3:

> **Input**: `s = "(1+(4+5+2)-3)+(6+8)"`<br/>
> **Output**: `23`

#### Constraints:

- `1 <= s.length <= 3 * 10⁵`
- `s` consists of digits, `'+'`, `'-'`, `'('`, `')'`, and `' '`.
- `s` represents a valid expression.
- `'+'` is not used as a unary operation (i.e., `"+1"` and `"+(2 + 3)"` is invalid).
- `'-'` could be used as a unary operation (i.e., `"-1"` and `"-(2 + 3)"` is valid).
- There will be no two consecutive operators in the input.
- Every number and running calculation will fit in a signed 32-bit integer.`

## Solutions

### Solution 1

```java
public int calculate(String s) {
    return simplifyBrackets(s.replaceAll(" ", ""));
}

private int simplifyBrackets(String s) {
    int lastIndexOfOpen = s.lastIndexOf("(");
    int firstIndexOfClose = s.indexOf(")", lastIndexOfOpen);
    if (lastIndexOfOpen == -1) {
        return calculateSimplified(s);
    }
    int result = calculateSimplified(s.substring(lastIndexOfOpen + 1, firstIndexOfClose));
    return simplifyBrackets(s.substring(0, lastIndexOfOpen) + result + s.substring(firstIndexOfClose + 1));
}

private int calculateSimplified(String sWithinBracket) {
    int result = 0;
    sWithinBracket = sWithinBracket.replaceAll("--", "+");
    for (String add : sWithinBracket.split("\\+")) {
        if (add.contains("-")) {
            String[] subArray = add.split("-");
            boolean firstNumIsNegative = add.startsWith("-");
            for (int i = 0; i < subArray.length; i++) {
                if (i == 0 && !subArray[i].isEmpty()) {
                    result = result + (firstNumIsNegative ? -1 : 1) * strToInt(subArray[i]);
                } else {
                    result = result - strToInt(subArray[i]);
                }
            }
        } else {
            result = result + strToInt(add);
        }
    }
    return result;
}

private int strToInt(String s) {
  return s.isEmpty() ? 0 : Integer.parseInt(s);
}
```

#### Complexities

- `Time Complexity`: O(n²)
    - where `n` is the length of the input string `s`.
    - This is because for each opening bracket, we are recursively simplifying the string by removing the innermost brackets and calculating the result.
    - This process may repeat for each bracket in the string, leading to a worst-case scenario where each character in the string is processed multiple times.
- `Space Complexity`: O(n)
    - where `n` is the length of the input string `s`.
    - This is because we are using recursion to simplify the brackets, which may lead to multiple recursive calls being stored on the call stack.
    - Additionally, we are creating new strings when simplifying the brackets, which adds to the space complexity.

### Solution 2

```java

```

#### Complexities

- `Time Complexity`: ?
    - ?
- `Space Complexity`: ?
    - ?

> [!TIP]
> ?