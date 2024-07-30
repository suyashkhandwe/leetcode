# Problem 20. Valid Parentheses

> [!NOTE]
> [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/description/?envType=study-plan-v2&envId=top-interview-150)

Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

### Examples

#### Example 1:

> **Input**: `s = "()"`<br/>
> **Output**: `true`

#### Example 2:

> **Input**: `s = "()[]{}"`<br/>
> **Output**: `true`

#### Example 3:

> **Input**: `s = "(]"`<br/>
> **Output**: `false`

#### Constraints:

- `1 <= s.length <= 10⁴`
- `s` consists of parentheses only `'()[]{}'.`

## Solutions

### Solution 1

```java
public boolean isValid(String s) {
    Stack<Character> parenthesisStack = new Stack<>();
    
    for (char current : s.toCharArray()) {
        if (current == '(' || current == '{' || current == '[') {
            parenthesisStack.push(current);
        } else {
            if (parenthesisStack.isEmpty()) {
                return false;
            } else {
                char top = parenthesisStack.pop();
                if ((current == ')' && top != '(')
                        || (current == '}' && top != '{')
                        || (current == ']' && top != '[')) {
                    return false;
                }
            }
        }
    }
    return parenthesisStack.isEmpty();
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the length of the input string.
    - This is because we iterate through each character in the string once.
- `Space Complexity`: O(n)
    - In the worst case scenario where all opening parentheses are present in the input string.
    - This is because the `stack` can potentially hold all opening parentheses before encountering any closing parentheses.
