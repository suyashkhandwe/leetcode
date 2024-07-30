# Problem 150. Evaluate Reverse Polish Notation

> [!NOTE]
> [150. Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/description/?envType=study-plan-v2&envId=top-interview-150)

You are given an array of strings `tokens` that represents an arithmetic expression in a [Reverse Polish Notation](http://en.wikipedia.org/wiki/Reverse_Polish_notation).

Evaluate the expression. Return _an integer that represents the value of the expression_.

**Note** that:

- The valid operators are `'+'`, `'-'`, `'*'`, and `'/'`. 
- Each operand may be an integer or another expression.
- The division between two integers always **truncates toward zero**.
- There will not be any division by zero.
- The input represents a valid arithmetic expression in a reverse polish notation.
- The answer and all the intermediate calculations can be represented in a **32-bit** integer.

### Examples

#### Example 1:

> **Input**: `tokens = ["2","1","+","3","*"]`<br/>
> **Output**: `9`<br/>
> **Explanation**: `((2 + 1) * 3) = 9`

#### Example 2:

> **Input**: `tokens = ["4","13","5","/","+"]`<br/>
> **Output**: `6`<br/>
> **Explanation**: `(4 + (13 / 5)) = 6`

#### Example 3:

> **Input**: `tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]`<br/>
> **Output**: `12`<br/>
> **Explanation**: `((10 * (6 / ((9 + 3) * -11))) + 17) + 5`<br/>
> `= ((10 * (6 / (12 * -11))) + 17) + 5`<br/>
> `= ((10 * (6 / -132)) + 17) + 5`<br/>
> `= ((10 * 0) + 17) + 5`<br/>
> `= (0 + 17) + 5`<br/>
> `= 17 + 5`<br/>
> `= 22`

#### Constraints:

- `1 <= tokens.length <= 10⁴`
- `tokens[i]` is either an operator: `"+"`, `"-"`, `"*"`, or `"/"`, or an integer in the range `[-200, 200]`.

## Solutions

### Solution 1

```java
public int evalRPN(String[] tokens) {
    Stack<Integer> tokenStack = new Stack<>();

    for (int i = 0; i < tokens.length; i++) {
        switch (tokens[i]) {
            case "+":
                tokenStack.add(tokenStack.pop() + tokenStack.pop());
                break;
            case "*":
                tokenStack.add(tokenStack.pop() * tokenStack.pop());
                break;
            case "-":
                int right = tokenStack.pop();
                tokenStack.add(tokenStack.pop() - right);
                break;
            case "/":
                int divisor = tokenStack.pop();
                tokenStack.add(tokenStack.pop() / divisor);
                break;
            default:
                tokenStack.add(Integer.valueOf(tokens[i]));
                break;
        }
    }
    return tokenStack.pop();
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the number of tokens in the input array.
    - This is because we iterate through each `token` in the array exactly once.
- `Space Complexity`: O(n)
    - This is because we use a `stack` to store the operands during the evaluation process.
    - The size of the stack can grow up to the number of `tokens` in the input array.
