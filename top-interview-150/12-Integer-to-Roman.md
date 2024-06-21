# Problem 12. Integer to Roman

> [!NOTE]
> [12. Integer to Roman](https://leetcode.com/problems/integer-to-roman/description/?envType=study-plan-v2&envId=top-interview-150)

Seven different symbols represent Roman numerals with the following values:

| Symbol    | Value |
| ------    | ----- |
| I	        | 1     |
| V	        | 5     |
| X	        | 10    |
| L	        | 50    |
| C	        | 100   |
| D	        | 500   |
| M	        | 1000  |

Roman numerals are formed by appending the conversions of decimal place values from highest to lowest. Converting a decimal place value into a Roman numeral has the following rules:
- If the value does not start with 4 or 9, select the symbol of the maximal value that can be subtracted from the input, append that symbol to the result, subtract its value, and convert the remainder to a Roman numeral.
- If the value starts with 4 or 9 use the subtractive form representing one symbol subtracted from the following symbol, for example, 4 is 1 (I) less than 5 (V): IV and 9 is 1 (I) less than 10 (X): IX. Only the following subtractive forms are used: 4 (IV), 9 (IX), 40 (XL), 90 (XC), 400 (CD) and 900 (CM).
- Only powers of 10 (I, X, C, M) can be appended consecutively at most 3 times to represent multiples of 10. You cannot append 5 (V), 50 (L), or 500 (D) multiple times. If you need to append a symbol 4 times use the subtractive form.

Given an integer, convert it to a Roman numeral.

### Examples

#### Example 1:

> **Input**: `3749`
> **Output**: `MMMDCCXLIX`
> **Explanation**:
> - `3000 = MMM as 1000 (M) + 1000 (M) + 1000 (M)`
> - `700 = DCC as 500 (D) + 100 (C) + 100 (C)`
> - `40 = XL as 10 (X) less of 50 (L)`
> - `9 = IX as 1 (I) less of 10 (X)`
> `Note`: 49 is not 1 (I) less of 50 (L) because the conversion is based on decimal places

#### Example 2:

> **Input**: `58`
> **Output**: `LVIII`
> **Explanation**:
> - `50 = L`
> - `8 = VIII`

#### Example 3:

> **Input**: `1994`
> **Output**: `MCMXCIV`
> **Explanation**:
> - `1000 = M`
> - `900 = CM`
> - `90 = XC`
> - `4 = IV`

#### Constraints:

- `1 <= num <= 3999`

## Solutions

### Solution 1

```java
public String intToRoman(int num) {
    int thousands = num / 1000;
    num -= thousands * 1000;

    int hundreds = num / 100;
    num -= hundreds * 100;

    int tens = num / 10;
    num -= tens * 10;

    int ones = num;

    String roman = "M".repeat(thousands);
    if (hundreds == 9) {
        roman += "CM";
    } else if (hundreds >= 5) {
        roman += "D" + (hundreds > 5 ? "C".repeat(hundreds - 5) : "");
    } else if (hundreds == 4) {
        roman += "CD";
    } else if (hundreds > 0) {
        roman += "C".repeat(hundreds);
    }

    if (tens == 9) {
        roman += "XC";
    } else if (tens >= 5) {
        roman += "L" + (tens > 5 ? "X".repeat(tens - 5) : "");
    } else if (tens == 4) {
        roman += "XL";
    } else if (tens > 0) {
        roman += "X".repeat(tens);
    }

    if (ones == 9) {
        roman += "IX";
    } else if (ones >= 5) {
        roman += "V" + (ones > 5 ? "I".repeat(ones - 5) : "");
    } else if (ones == 4) {
        roman += "IV";
    } else if (ones > 0) {
        roman += "I".repeat(ones);
    }
    return roman;
}
```

#### Complexities

- `Time Complexity`: O(1) 
    - the number of operations performed is constant regardless of the input size
- `Space Complexity`: O(1)
    - the amount of extra space used is constant and does not depend on the input size

### Solution 2

```java
public String intToRoman(int num) {
    List<Map.Entry<Integer, String>> intToRoman = List.of(
            new AbstractMap.SimpleEntry<>(1000, "M"),
            new AbstractMap.SimpleEntry<>(900, "CM"),
            new AbstractMap.SimpleEntry<>(500, "D"),
            new AbstractMap.SimpleEntry<>(400, "CD"),
            new AbstractMap.SimpleEntry<>(100, "C"),
            new AbstractMap.SimpleEntry<>(90, "XC"),
            new AbstractMap.SimpleEntry<>(50, "L"),
            new AbstractMap.SimpleEntry<>(40, "XL"),
            new AbstractMap.SimpleEntry<>(10, "X"),
            new AbstractMap.SimpleEntry<>(9, "IX"),
            new AbstractMap.SimpleEntry<>(5, "V"),
            new AbstractMap.SimpleEntry<>(4, "IV"),
            new AbstractMap.SimpleEntry<>(1, "I"));

    StringBuilder roman = new StringBuilder();
    int i = 0;
    while (i < intToRoman.size()) {
        if (num >= intToRoman.get(i).getKey()) {
            roman.append(intToRoman.get(i).getValue());
            num -= intToRoman.get(i).getKey();
        } else {
            i++;
        }
    }
    return roman.toString();
}

```

#### Complexities

- `Time Complexity`: O(1) 
    - the number of operations performed is constant regardless of the input size
- `Space Complexity`: O(1)
    - the amount of extra space used is constant and does not depend on the input size

### Solution 3

```java
public String intToRoman(int num) {    
    String[] thousands = new String[]{"", "M", "MM", "MMM"};
    String[] hundreds = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] tens = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] ones = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    return thousands[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 100 / 10] + ones[num % 10];
}
```

#### Complexities

- `Time Complexity`: O(1) 
    - the number of operations performed is constant regardless of the input size
- `Space Complexity`: O(1)
    - the amount of extra space used is constant and does not depend on the input size

> [!TIP]
> - All solutions have the same complexities.
> - `Solution 1` is the most complex.
> - `Solution 2` uses a predefined map taking advantage of the input constraints.
> - `Solution 3` abuses the input constraints and hardcodes a lot of mapping.
> `Solution 1` and `Solution 3` have the same runtime while `Solution 2` is 50% faster.