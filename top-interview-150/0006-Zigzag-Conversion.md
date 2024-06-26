# Problem 6. Zigzag Conversion

> [!NOTE]
> [6. Zigzag Conversion](https://leetcode.com/problems/zigzag-conversion/description/?envType=study-plan-v2&envId=top-interview-150)

The string `"PAYPALISHIRING"` is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

> `P   A   H   N`
> `A P L S I I G`
> `Y   I   R`
And then read line by line: `"PAHNAPLSIIGYIR"`

Write the code that will take a string and make this conversion given a number of rows:

> `string convert(string s, int numRows);`

### Examples

#### Example 1:

> **Input**: `s = "PAYPALISHIRING", numRows = 3`
> **Output**: `"PAHNAPLSIIGYIR"`

#### Example 2:

> **Input**: `s = "PAYPALISHIRING", numRows = 4`
> **Output**: `"PINALSIGYAHRPI"`
> **Explanation**: 
> `P     I    N`
> `A   L S  I G`
> `Y A   H R`
> `P     I`

#### Example 3:

> **Input**: `s = "A", numRows = 1`
> **Output**: `"A"`

#### Constraints:

- `1 <= s.length <= 1000`
- `s consists of English letters (lower-case and upper-case), ',' and '.'`
- `1 <= numRows <= 1000`

## Solutions

### Solution 1

```java
public String convert(String s, int numRows) {
    char[][] zigzagChars = new char[numRows][s.length()];
    int r = 0;
    int c = 0;
    int idx = 0;
    while (idx < s.length()) {
        zigzagChars[r++][c] = s.charAt(idx++);
        if (r == numRows) {
            r--;
            while (r > 0 && idx < s.length()) {
                zigzagChars[--r][++c] = s.charAt(idx++);
            }
            r++;
            if (r == numRows) {
                r--;
                c++;
            }
        }
    }
    StringBuilder convertedStringBuilder = new StringBuilder();
    for (char[] zigzagChar : zigzagChars) {
        for (char value : zigzagChar) {
            if (value != '\u0000' && value != ' ') {
                convertedStringBuilder.append(value);
            }
        }
    }
    return convertedStringBuilder.toString().trim();
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterate through each character in the input string once to fill the zigzagChars array
- `Space Complexity`: O(n)
    - create a 2D array of size `numRows x n` to store the characters in zigzag pattern

### Solution 2

```java
public String convert(String s, int numRows) {
    if (numRows <= 1) {
        return s;
    }

    int nonDiagonalDelta = 2 * (numRows - 1);
    StringBuilder convertedBuilder = new StringBuilder();
    for (int i = 0; i < numRows; i++) {
        int sIdx = i;
        while (sIdx < s.length()) {
            convertedBuilder.append(s.charAt(sIdx));
            if (i != 0 && i != numRows - 1) {
                int prevDiagonalSIdx = sIdx + nonDiagonalDelta - (2 * i);
                if (prevDiagonalSIdx < s.length()) {
                    convertedBuilder.append(s.charAt(prevDiagonalSIdx));
                }
            }
            sIdx += nonDiagonalDelta;
        }
    }
    return convertedBuilder.toString();
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterate through each character in the input string once to build the converted string
- `Space Complexity`: O(n)
    - use a `StringBuilder` to store the converted string, which can potentially be the same length as the input string.
    - Additionally, a constant amount of extra space is used for variables and calculations, so the overall space complexity is O(n)

> [!TIP]
> Although the complexities of both the solutions are the same, the second solution is significantly faster due to lower processing.
