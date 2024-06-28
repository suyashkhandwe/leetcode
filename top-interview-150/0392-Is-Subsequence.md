# Problem 392. Is Subsequence

> [!NOTE]
> [392. Is Subsequence](https://leetcode.com/problems/is-subsequence/description/?envType=study-plan-v2&envId=top-interview-150)

Given two strings `s` and `t`, return `true` if `s` is a subsequence of `t`, or `false` otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., `"ace"` is a subsequence of `"abcde"` while `"aec"` is not).

### Examples

#### Example 1:

> **Input**: `s = "abc", t = "ahbgdc"`
> **Output**: `true`

#### Example 2:

> **Input**: `s = "axc", t = "ahbgdc"`
> **Output**: `false`

#### Constraints:

- `0 <= s.length <= 100`
- `0 <= t.length <= 104`
- `s and t consist only of lowercase English letters.`

## Solutions

### Solution 1

```java
import java.util.regex.Pattern;

public boolean isSubsequence(String s, String t) {
    return Pattern.compile(".*" + String.join(".*", s.split("")) + ".*").matcher(t).matches();
}
```

#### Complexities

- `Time Complexity`: O(n*m)
    - where `n` is the length of string `s` and `m` is the length of string `t`.
    - because for each character in string `s`, the matcher needs to check each character in string `t`.
- `Space Complexity`: O(n)
    - where `n` is the length of the string `t`.
    - because the regular expression pattern is constructed based on the characters of string `s`, and the space required for the pattern is proportional to the length of string `t`

> [!CAUTION]
> This would result in timeout due to high time complexity.

### Solution 2

```java
public boolean isSubsequence(String s, String t) {
    int sIdx = 0;
    int tIdx = 0;
    boolean isSubsequence = false;
    while (tIdx < t.length() && sIdx < s.length()) {
        if (s.charAt(sIdx) == t.charAt(tIdx++)) {
            sIdx++;
        }
        isSubsequence = sIdx == s.length();
    }
    return isSubsequence || s.isEmpty();
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterates through the characters of the strings `s` and `t`.
    - In the worst case scenario, the loop will iterate through all characters of `t` and `s`.
- `Space Complexity`: O(1)
    - use a constant amount of extra space regardless of the input size.

### Solution 3

```java
public boolean isSubsequence(String s, String t) {
    int sLen = s.length();
    int tLen = t.length();
    if (sLen < 1) {
        return true;
    }

    int sIdx = 0;
    int tIdx = 0;
    char[] sChars = s.toCharArray();
    char[] tChars = t.toCharArray();
    while (tIdx < tLen && sIdx < sLen) {
        if (sChars[sIdx] == tChars[tIdx++]) {
            sIdx++;
        }
    }
    return sIdx == sLen;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterates through the characters of the strings `s` and `t`.
    - In the worst case scenario, the loop will iterate through all characters of `t` and `s`.
- `Space Complexity`: O(1)
    - use a constant amount of extra space regardless of the input size.

> [!TIP]
> `Solution 2` and `Solution 3` have the same complexities, however, `Solution 3` is faster since we short circuit some paths.
