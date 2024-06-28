# Problem 13. Roman to Integer

> [!NOTE]
> [13. Roman to Integer](https://leetcode.com/problems/roman-to-integer/description/?envType=study-plan-v2&envId=top-interview-150)

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

|   Symbol  |   Value   |
|   ------  |   -----   |
|   I       |   1       |
|   V       |   5       |
|   X       |   10      |
|   L       |   50      |
|   C       |   100     |
|   D       |   500     |
|   M       |   1000    |

For example, `2 is written as II` in Roman numeral, just two ones added together. `12 is written as XII`, which is simply `X + II`. The number `27 is written as XXVII`, which is `XX + V + II`.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

- `I` can be placed before `V (5)` and `X (10)` to make `4` and `9`.
- `X` can be placed before `L (50)` and `C (100)` to make `40` and `90`.
- `C` can be placed before `D (500)` and `M (1000)` to make `400` and `900`.
Given a roman numeral, convert it to an integer.

### Examples

#### Example 1:

> **Input**: `s = "III"`<br/>
> **Output**: `3`<br/>
> **Explanation**: `III = 3`

#### Example 2:

> **Input**: `s = "LVIII"`<br/>
> **Output**: `58`<br/>
> **Explanation**: `L = 50, V= 5, III = 3`

#### Example 3:

> **Input**: `s = "MCMXCIV"`<br/>
> **Output**: `1994`<br/>
> **Explanation**: `M = 1000, CM = 900, XC = 90 and IV = 4`

#### Constraints:

- `1 <= s.length <= 15`
- `s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').`
- `It is guaranteed that s is a valid roman numeral in the range [1, 3999].`

## Solutions

### Solution 1

```java
private static final Map<String, Integer> SYMBOL_TO_INT_MAP = new HashMap<>();

static {
    SYMBOL_TO_INT_MAP.put("I", 1);
    SYMBOL_TO_INT_MAP.put("IV", 4);
    SYMBOL_TO_INT_MAP.put("V", 5);
    SYMBOL_TO_INT_MAP.put("IX", 9);
    SYMBOL_TO_INT_MAP.put("X", 10);
    SYMBOL_TO_INT_MAP.put("XL", 40);
    SYMBOL_TO_INT_MAP.put("L", 50);
    SYMBOL_TO_INT_MAP.put("XC", 90);
    SYMBOL_TO_INT_MAP.put("C", 100);
    SYMBOL_TO_INT_MAP.put("CD", 400);
    SYMBOL_TO_INT_MAP.put("D", 500);
    SYMBOL_TO_INT_MAP.put("CM", 900);
    SYMBOL_TO_INT_MAP.put("M", 1000);
}

public int romanToInt(String s) {
    char[] romanChars = s.toCharArray();
    int returnInt = 0;
    int i = 0;
    while (i < romanChars.length) {
        if (romanChars[i] == 'I') {
            if (i + 1 < romanChars.length && (romanChars[i + 1] == 'V' || romanChars[i + 1] == 'X')) {
                returnInt += SYMBOL_TO_INT_MAP.get(romanChars[i] + String.valueOf(romanChars[i + 1]));
                i = i + 2;
            } else {
                returnInt += SYMBOL_TO_INT_MAP.get(String.valueOf(romanChars[i]));
                i++;
            }
        } else if (romanChars[i] == 'X') {
            if (i + 1 < romanChars.length && (romanChars[i + 1] == 'L' || romanChars[i + 1] == 'C')) {
                returnInt += SYMBOL_TO_INT_MAP.get(romanChars[i] + String.valueOf(romanChars[i + 1]));
                i = i + 2;
            } else {
                returnInt += SYMBOL_TO_INT_MAP.get(String.valueOf(romanChars[i]));
                i++;
            }
        } else if (romanChars[i] == 'C') {
            if (i + 1 < romanChars.length && (romanChars[i + 1] == 'D' || romanChars[i + 1] == 'M')) {
                returnInt += SYMBOL_TO_INT_MAP.get(romanChars[i] + String.valueOf(romanChars[i + 1]));
                i = i + 2;
            } else {
                returnInt += SYMBOL_TO_INT_MAP.get(String.valueOf(romanChars[i]));
                i++;
            }
        } else {
            returnInt += SYMBOL_TO_INT_MAP.get(String.valueOf(romanChars[i]));
            i++;
        }
    }
    return returnInt;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterate through each character in the input string once
- `Space Complexity`: O(1)
    - uses a fixed amount of extra space (for the map) regardless of the size of the input string

### Solution 2

```java
public int romanToInt(String s) {
    int returnInt = 0;
    int i = 0;
    while (i < s.length()) {
        if (s.charAt(i) == 'V') {
            returnInt += 5;
            i++;
        } else if (s.charAt(i) == 'L') {
            returnInt += 50;
            i++;
        } else if (s.charAt(i) == 'D') {
            returnInt += 500;
            i++;
        } else if (s.charAt(i) == 'M') {
            returnInt += 1000;
            i++;
        } else if (s.charAt(i) == 'I') {
            if (i + 1 < s.length() && s.charAt(i + 1) == 'V') {
                returnInt += 4;
                i += 2;
            } else if (i + 1 < s.length() && s.charAt(i + 1) == 'X') {
                returnInt += 9;
                i += 2;
            } else {
                returnInt += 1;
                i++;
            }
        } else if (s.charAt(i) == 'X') {
            if (i + 1 < s.length() && s.charAt(i + 1) == 'L') {
                returnInt += 40;
                i += 2;
            } else if (i + 1 < s.length() && s.charAt(i + 1) == 'C') {
                returnInt += 90;
                i += 2;
            } else {
                returnInt += 10;
                i++;
            }
        } else if (s.charAt(i) == 'C') {
            if (i + 1 < s.length() && s.charAt(i + 1) == 'D') {
                returnInt += 400;
                i += 2;
            } else if (i + 1 < s.length() && s.charAt(i + 1) == 'M') {
                returnInt += 900;
                i += 2;
            } else {
                returnInt += 100;
                i++;
            }
        }
    }
    return returnInt;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterate through the input string once
- `Space Complexity`: O(1)
    - uses a fixed amount of extra space for the return value

> [!TIP]
> Although the complexities are the same, the second solution is more efficient.

### Solution 3

```java
public int romanToInt(String s) {
    int returnInt = 0;
    int previousInt = 0;
    int currentInt = 0;
    for (int i = s.length() - 1; i >= 0; i--) {
        currentInt = convertRomanCharToInt(s.charAt(i));
        if (currentInt < previousInt) {
            returnInt -= currentInt;
        } else {
            returnInt += currentInt;
        }
        previousInt = currentInt;
    }
    return returnInt;
}

private int convertRomanCharToInt(final char romanChar) {
    return switch (romanChar) {
        case 'I' -> 1;
        case 'V' -> 5;
        case 'X' -> 10;
        case 'L' -> 50;
        case 'C' -> 100;
        case 'D' -> 500;
        case 'M' -> 1000;
        default -> 0;
    };
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterate through the input string once
- `Space Complexity`: O(1)
    - uses a fixed amount of extra space for the return value

> [!TIP]
> Although the complexities are the same, the third solution is more efficient than the other two as it uses the sequencing of Roman numbers representation where the numbers are written from largest to smallest (additive) and if a smaller number comes first, it is to be subtracted.

