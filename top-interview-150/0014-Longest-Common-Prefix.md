# Problem 14. Longest Common Prefix

> [!NOTE]
> [14. Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/description/?envType=study-plan-v2&envId=top-interview-150)

Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

### Examples

#### Example 1:

> **Input**: `strs = ["flower","flow","flight"]`<br/>
> **Output**: `"fl"`

#### Example 2:

> **Input**: `strs = ["dog","racecar","car"]`<br/>
> **Output**: `""`<br/>
> **Explanation**: `There is no common prefix among the input strings.`

#### Constraints:

- `1 <= strs.length <= 200`
- `0 <= strs[i].length <= 200`
- `strs[i] consists of only lowercase English letters.`

## Solutions

### Solution 1

```java
public String longestCommonPrefix(String[] strs) {
    String potentialLongestCommonPrefix = strs[0];

    for (int strsIdx = 1; strsIdx < strs.length; strsIdx++) {
        while (true) {
            if (strs[strsIdx].startsWith(potentialLongestCommonPrefix)) {
                break;
            } else {
                potentialLongestCommonPrefix = potentialLongestCommonPrefix.substring(0, potentialLongestCommonPrefix.length() - 1);
            }
        }
        if (potentialLongestCommonPrefix.isEmpty()) {
            break;
        }
    }
    return potentialLongestCommonPrefix;
}
```

#### Complexities

- `Time Complexity`: O(n*m)
    - where n is the number of strings in the input array and m is the average length of the strings
    - iterate through each character of the potential longest common prefix for each string in the array
- `Space Complexity`: O(1)
    - using a constant amount of extra space regardless of the input size

### Solution 2

```java
public String longestCommonPrefix(String[] strs) {
    String potentialLongestCommonPrefix = "";
    int minLen = Integer.MAX_VALUE;
    // Get only the shortest string as the default longest prefix since the longest prefix can't be longer than this.
    for (String str : strs) {
        if (str.length() < minLen) {
            minLen = str.length();
            potentialLongestCommonPrefix = str;
        }
    }
    int i = 0;
    while (i < strs.length) {
        if (strs[i].startsWith(potentialLongestCommonPrefix)) {
            i++;
        } else {
            potentialLongestCommonPrefix = potentialLongestCommonPrefix.substring(0, potentialLongestCommonPrefix.length() - 1);
        }
    }
    return potentialLongestCommonPrefix;
}
```

#### Complexities

- `Time Complexity`: O(n*m)
    - where n is the number of strings in the input array and m is the average length of the strings
    - iterate through each character of the potential longest common prefix for each string in the array
- `Space Complexity`: O(1)
    - using a constant amount of extra space regardless of the input size

> [!TIP]
> `Solution 1` and `Solution 2` are fast, though the time complexity is high and can be reduced further by comparing only the first and last elements.