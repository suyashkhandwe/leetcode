# Problem 125. Valid Palindrome

> [!NOTE]
> [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/description/?envType=study-plan-v2&envId=top-interview-150)

A phrase is a **palindrome** if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

### Examples

#### Example 1:

> **Input**: `s = "A man, a plan, a canal: Panama"`<br/>
> **Output**: `true`<br/>
> **Explanation**: `"amanaplanacanalpanama" is a palindrome.`

#### Example 2:

> **Input**: `s = "race a car"`<br/>
> **Output**: `false`<br/>
> **Explanation**: `"raceacar" is not a palindrome.`

#### Example 3:

> **Input**: `s = " "`<br/>
> **Output**: `true`<br/>
> **Explanation**: `s is an empty string "" after removing non-alphanumeric characters. Since an empty string reads the same forward and backward, it is a palindrome.`

#### Constraints:

- `1 <= s.length <= 2 * 105`
- `s consists only of printable ASCII characters.`

## Solutions

### Solution 1

```java
import java.util.Locale;

int alphaStart = 'a';
int alphaEnd = 'z';
int numbersStart = '0';
int numbersEnd = '9';

public boolean isPalindrome(String s) {
    char[] chars = s.toLowerCase(Locale.ROOT).toCharArray();
    int l = 0;
    int r = s.length() - 1;
    while (l <= r) {
        int left = chars[l];
        int right = chars[r];
        if (!isAlphaNumeric(left)) {
            l++;
            continue;
        }
        if (!isAlphaNumeric(right)) {
            r--;
            continue;
        }
        l++;
        r--;
        if (left != right) {
            return false;
        }
    }
    return true;
}

private boolean isAlphaNumeric(int asciiChar) {
    return (asciiChar >= alphaStart && asciiChar <= alphaEnd)
            || (asciiChar >= numbersStart && asciiChar <= numbersEnd);
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterates through the characters of the input string 's' using two pointers 'l' and 'r' once (half way through)
- `Space Complexity`: O(1)
    - use constant extra space

### Solution 2

```java
import java.util.Locale;

public boolean isPalindrome(String s) {
    char[] chars = s.toLowerCase(Locale.ROOT).toCharArray();
    int l = 0;
    int r = s.length() - 1;
    while (l <= r) {
        if (!isAlphaNumeric(chars[l])) {
            l++;
        } else if (!isAlphaNumeric(chars[r])) {
            r--;
        } else if (chars[l] != chars[r]) {
            return false;
        } else {
            l++;
            r--;
        }
    }
    return true;
}

private boolean isAlphaNumeric(int asciiChar) {
    return (asciiChar >= 'a' && asciiChar <= 'z')
            || (asciiChar >= '0' && asciiChar <= '9');
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterates through the characters of the input string 's' using two pointers 'l' and 'r' once (half way through)
- `Space Complexity`: O(1)
    - use constant extra space

> [!TIP]
> The second solution ekes out that 1ms more performance.