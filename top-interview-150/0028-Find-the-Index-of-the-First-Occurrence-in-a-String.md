# Problem 28. Find the Index of the First Occurrence in a String

> [!NOTE]
> [28. Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/?source=submission-ac)

Given two strings `needle` and `haystack`, return the index of the first occurrence of `needle` in `haystack`, or `-1` if `needle` is not part of `haystack`.

### Examples

#### Example 1:

> **Input**: `haystack = "sadbutsad", needle = "sad"`<br/>
> **Output**: `0`<br/>
> **Explanation**: `"sad" occurs at index 0 and 6. The first occurrence is at index 0, so we return 0.`

#### Example 2:

> **Input**: `haystack = "leetcode", needle = "leeto"`<br/>
> **Output**: `-1`<br/>
> **Explanation**: `"leeto" did not occur in "leetcode", so we return -1.`

#### Constraints:

- `1 <= haystack.length, needle.length <= 104`
- `haystack and needle consist of only lowercase English characters.`

## Solutions

### Solution 1

```java
public int strStr(String haystack, String needle) {
    return haystack.indexOf(needle);
}
```

#### Complexities

- `Time Complexity`: O(n*m)
    - indexOf method in Java has a time complexity of O(n*m) in the worst case scenario, where n is the length of the haystack and m is the length of the needle.
- `Space Complexity`: O(1)
    - no extra space is used that grows with the input size

### Solution 2

```java
public int strStr(String haystack, String needle) {
    if (needle.length() > haystack.length()) {
        return -1;
    }
    char[] haystackChars = haystack.toCharArray();
    char[] needleChars = needle.toCharArray();
    int returnIdx = -1;
    int hIdx = 0;
    while (hIdx < haystack.length()) {
        if (haystackChars[hIdx] == needleChars[0] && hIdx + needle.length() <= haystack.length()) {
            for (int nIdx = 0; nIdx < needle.length(); nIdx++) {
                if (haystackChars[hIdx + nIdx] != needleChars[nIdx]) {
                    returnIdx = -1;
                    break;
                }
                returnIdx = hIdx;
            }
        }
        hIdx++;
        if (returnIdx > -1) {
            break;
        }
    }
    return returnIdx;
}
```

#### Complexities

- `Time Complexity`: O(m*n)
    - iterate through the haystack characters and for each character, we potentially iterate through the needle characters to check for a match
- `Space Complexity`: O(1)
    - constant amount of extra space regardless of the input size

### Solution 3

```java
public int strStr(String haystack, String needle) {
    int returnIdx = -1;
    int hIdx = 0;
    char[] haystackChars = haystack.toCharArray();
    char[] needleChars = needle.toCharArray();
    int haystackLen = haystack.length();
    int needleLen = needle.length();
    while (hIdx <= haystackLen - needleLen) {
        returnIdx = hIdx;
        for (int nIdx = 0; nIdx < needleLen; nIdx++) {
            if (haystackChars[hIdx + nIdx] != needleChars[nIdx]) {
                returnIdx = -1;
                break;
            }
        }
        if (returnIdx > -1) {
            return returnIdx;
        }
        hIdx++;
    }
    return returnIdx;
}
```

#### Complexities

- `Time Complexity`: O((m-n)*n)
    - where `m is the length of the haystack` and `n is the length of the needle`
    - outer loop iterates through the `haystack` characters, and for each character, the inner loop compares the `needle` characters
- `Space Complexity`: O(1)
    - constant amount of extra space regardless of the input size

> [!TIP]
> Solution 3 takes advantage of reducing the looping till `haystackLen - needleLen` which is the last possible index to find the `needle`.
