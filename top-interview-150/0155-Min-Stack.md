# Problem 155. Min Stack

> [!NOTE]
> [155. Min Stack](https://leetcode.com/problems/min-stack/description/?envType=study-plan-v2&envId=top-interview-150)

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the `MinStack` class:

- `MinStack()` initializes the stack object. 
- `void push(int val)` pushes the element `val` onto the stack.
- `void pop()` removes the element on the top of the stack.
- `int top()` gets the top element of the stack.
- `int getMin()` retrieves the minimum element in the stack.

You must implement a solution with `O(1)` time complexity for each function.

### Examples

#### Example 1:

> **Input**: `["MinStack","push","push","push","getMin","pop","top","getMin"]`<br/>
> `[[],[-2],[0],[-3],[],[],[],[]]`<br/>
> **Output**: `[null,null,null,null,-3,null,0,-2]`<br/>
> **Explanation**: 
> `MinStack minStack = new MinStack();`<br/>
> `minStack.push(-2);`<br/>
> `minStack.push(0);`<br/>
> `minStack.push(-3);`<br/>
> `minStack.getMin(); // return -3`<br/>
> `minStack.pop();`<br/>
> `minStack.top();    // return 0`<br/>
> `minStack.getMin(); // return -2``<br/>

#### Constraints:

- `-2³¹ <= val <= 2³¹ - 1`
- Methods `pop`, `top` and `getMin` operations will always be called on **non-empty** stacks.
- At most `3 * 10⁴` calls will be made to `push`, `pop`, `top`, and `getMin`.

## Solutions

### Solution 1

```java
public class MinStack {

    private static class Value {
        private final int val;
        private final int minAtVal;

        Value(final int val, final int minAtVal) {
            this.val = val;
            this.minAtVal = minAtVal;
        }
    }

    private final Value[] values;
    private int topIndex;

    public MinStack() {
        values = new Value[30000];
        topIndex = -1;
    }

    public void push(int val) {
        int minAtVal = (topIndex == -1) ? val : Math.min(values[topIndex].minAtVal, val);
        values[++topIndex] = new Value(val, minAtVal);
    }

    public void pop() {
        values[topIndex--] = null;
    }

    public int top() {
        return values[topIndex].val;
    }

    public int getMin() {
        return values[topIndex].minAtVal;
    }
}
```

#### Complexities

- `Time Complexity`: O(1)
    - The time complexity of the push, pop, top, and getMin operations in the MinStack class is O(1).
    - This is because each operation involves constant time operations such as array element `access`, `comparison`, and `assignment`.
- `Space Complexity`: O(n)
    - Depends on the number of elements pushed to the stack.

### Solution 2

```java
public class MinStack {

    private final List<Integer> values;
    private final List<Integer> minVals;

    public MinStack() {
        values = new ArrayList<>();
        minVals = new ArrayList<>();
    }

    public void push(int val) {
        minVals.add((values.isEmpty()) ? val : Math.min(minVals.get(minVals.size() - 1), val));
        values.add(val);
    }

    public void pop() {
        if (values.remove(values.size() - 1) >= minVals.get(minVals.size() - 1)) {
            minVals.remove(minVals.size() - 1);
        }
    }

    public int top() {
        return values.get(values.size() - 1);
    }

    public int getMin() {
        return minVals.get(minVals.size() - 1);
    }
}
```

#### Complexities

- `Time Complexity`: O(1)
    - The time complexity of the `push`, `pop`, `top`, and `getMin` operations in the MinStack class is O(1),
    - This is because each operation involves constant time operations such as `adding/removing elements` from the end of the lists or `accessing elements` by index.
- `Space Complexity`: O(n)
  - Depends on the number of elements pushed to the stack.

> [!TIP]
> Both solutions have the same complexities.
