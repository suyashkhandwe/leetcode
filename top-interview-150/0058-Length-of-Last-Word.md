# Problem 58. Length of Last Word

> [!NOTE]
> [58. Length of Last Word](https://leetcode.com/problems/length-of-last-word/description/?envType=study-plan-v2&envId=top-interview-150)

Given a string s consisting of words and spaces, return the length of the last word in the string.
A word is a maximal substring consisting of non-space characters only.

### Examples

#### Example 1:

> **Input**: `Hello World`<br/>
> **Output**: `5`<br/>
> **Explanation**: The last word is "World" with length 5.

#### Example 2:

> **Input**: `   fly me   to   the moon  `<br/>
> **Output**: `4`<br/>
> **Explanation**: The last word is "moon" with length 4.

#### Example 3:

> **Input**: `luffy is still joyboy`<br/>
> **Output**: `6`<br/>
> **Explanation**: The last word is "joyboy" with length 6.

#### Constraints:

- `1 <= s.length <= 104`
- `s consists of only English letters and spaces ' '.`
- `There will be at least one word in s.`

## Solutions

### Solution 1

```java
public int lengthOfLastWord(String s) {
    Matcher matcher = Pattern.compile("( *([a-zA-Z]+) *)+").matcher(s);
    if (matcher.find()) {
        return matcher.group(matcher.groupCount()).length();
    }
    return -1;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - the regular expression pattern is applied to the input string once, and the matching process takes linear time in relation to the length of the input string
- `Space Complexity`: O(1)
    - the amount of extra space used does not depend on the size of the input string

### Solution 2

```java
public int lengthOfLastWord(String s) {
    s = s.trim();
    int i = s.length() - 1;
    int len = 0;
    while (i >= 0) {
        if (s.charAt(i) == ' ') {
            break;
        }
        len++;
        i--;
    }
    return len;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterate through the string once to find the length of the last word
- `Space Complexity`: O(1)
    - only use a constant amount of extra space for variables i, len, and the loop iterator

> [!TIP]
> Although, the 2 solutions have the same complexities, the second one is faster.