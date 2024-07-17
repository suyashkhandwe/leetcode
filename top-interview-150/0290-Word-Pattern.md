# Problem 290. Word Pattern

> [!NOTE]
> [290. Word Pattern](https://leetcode.com/problems/word-pattern/description/?envType=study-plan-v2&envId=top-interview-150)

Given a `pattern` and a string `s`, find if `s` follows the same pattern.

Here **follow** means a full match, such that there is a bijection between a letter in `pattern` and a **non-empty** word in `s`.

### Examples

#### Example 1:

> **Input**: `pattern = "abba", s = "dog cat cat dog"`<br/>
> **Output**: `true`<br/>

#### Example 2:

> **Input**: `pattern = "abba", s = "dog cat cat fish"`<br/>
> **Output**: `false`<br/>

#### Example 3:

> **Input**: `pattern = "aaaa", s = "dog cat cat dog"`<br/>
> **Output**: `false`<br/>

#### Constraints:

- `1 <= pattern.length <= 300`
- `pattern contains only lower-case English letters.`
- `1 <= s.length <= 3000`
- `s contains only lowercase English letters and spaces ' '.`
- `s does not contain any leading or trailing spaces.`
- `All the words in s are separated by a single space.`

## Solutions

### Solution 1

```java
public boolean wordPattern(String pattern, String s) {
    String[] sStrings = s.split(" ");
    if (pattern.length() != sStrings.length) {
        return false;
    }

    String[] sStringsMap = new String[26];
    int i = -1;
    while (++i < pattern.length()) {
        if (sStringsMap[pattern.charAt(i) - 97] == null) {
            for (String string : sStringsMap) {
                if (sStrings[i].equals(string)) {
                    return false;
                }
            }
            sStringsMap[pattern.charAt(i) - 97] = sStrings[i];
        }
        if (!sStringsMap[pattern.charAt(i) - 97].equals(sStrings[i])) {
            return false;
        }
    }
    return true;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the length of the pattern string.
    - This is because we iterate through the `pattern` string and split the input string `s` into an array of strings, which takes O(n) time.
    - Then, we iterate through the `pattern` string again to check and update the mapping, which also takes O(n) time.
- `Space Complexity`: O(1)
    - This is because we are using a fixed-size array of size 26 to store the mapping between characters in the `pattern` string and strings in the input string `s`.
