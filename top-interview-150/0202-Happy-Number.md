# Problem 202. Happy Number

> [!NOTE]
> [202. Happy Number](https://leetcode.com/problems/happy-number/description/?envType=study-plan-v2&envId=top-interview-150)

Write an algorithm to determine if a number `n` is happy.

A happy number is a number defined by the following process:

- Starting with any positive integer, replace the number by the sum of the squares of its digits.
- Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
- Those numbers for which this process ends in 1 are happy.
- Return `true` if `n` is a happy number, and `false` if not.

### Examples

#### Example 1:

> **Input**: `n = 19`<br/>
> **Output**: `true`<br/>
> **Explanation**:<br/>
> `1² + 9² = 82`<br/>
> `8² + 2² = 68`<br/>
> `6² + 8² = 100`<br/>
> `1² + 0² + 0² = 1`<br/>

#### Example 2:

> **Input**: `n = 2`<br/>
> **Output**: `false`<br/>

#### Constraints:

- `1 <= n <= 23¹ - 1`

## Solutions

### Solution 1

```java
public boolean isHappy(int n) {
    Set<Integer> uniqueProducts = new HashSet<>();
    while (true) {
        int happinessQuotient = 0;
        while (n > 0) {
            int remainder = n % 10;
            n = (n - remainder) / 10;
            happinessQuotient += remainder * remainder;
        }
        n = happinessQuotient;
        if (!uniqueProducts.add(happinessQuotient)) {
            return false;
        }
        if (n == 1) {
            return true;
        }
    }
}
```

#### Complexities

- `Time Complexity`: Can't be calculated.
    - The time complexity of this solution is difficult to determine precisely because it depends on the behavior of the input number `n`.
    - In the worst case scenario, where `n` is a very large number that results in a cycle of numbers that do not lead to 1, the algorithm could potentially run indefinitely.
    - However, in practice, the algorithm is expected to converge relatively quickly for most inputs.
- `Space Complexity`: O(log n)
    - This is because the size of the `uniqueProducts` set will grow as the algorithm iterates through different numbers.
    - The maximum number of unique products that can be stored in the set is limited by the number of digits in `n`, which is log(n) in base 10.

### Solution 2

```java
public boolean isHappy(int n) {
    int slow = n;
    int fast = computeHappinessQuotient(n);
    while (fast != 1 && slow != fast) {
        slow = computeHappinessQuotient(slow);
        fast = computeHappinessQuotient(computeHappinessQuotient(fast));
    }
    return fast == 1;
}

int computeHappinessQuotient(int n) {
    int productOfDigits = 0;
    while (n > 0) {
        int digit = n % 10;
        n = (n - digit) / 10;
        productOfDigits += digit * digit;
    }
    return productOfDigits;
}
```

#### Complexities

- `Time Complexity`: O(log n)
    - This is because the `computeHappinessQuotient` function iterates through the digits of the number `n`, which has `log(n)` digits.
    - The while loop in the `isHappy` function also runs in `O(log n)` time because it iterates until the `fast` pointer reaches `1` or until the `slow` and `fast` pointers meet.
- `Space Complexity`: O(1)
    - This is because the solution uses a constant amount of extra space regardless of the input size.

> [!TIP]
> `Solution 2` uses the [Floyd’s Cycle Finding Algorithm](https://www.geeksforgeeks.org/floyds-cycle-finding-algorithm/) or `Hare-Tortoise algorithm`.
