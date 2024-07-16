# Problem 205. Isomorphic Strings

> [!NOTE]
> [205. Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings/description/?envType=study-plan-v2&envId=top-interview-150)

Given two strings `s` and `t`, determine if they are **isomorphic**.

Two strings `s` and `t` are **isomorphic** if the characters in `s` can be replaced to get `t`.

- All occurrences of a character must be replaced with another character while preserving the order of characters.
- No two characters may map to the same character, but a character may map to itself.

### Examples

#### Example 1:

> **Input**: `s = "egg", t = "add"`<br/>
> **Output**: `true`<br/>

#### Example 2:

> **Input**: `s = "foo", t = "bar"`<br/>
> **Output**: `false`<br/>

#### Example 3:

> **Input**: `s = "paper", t = "title"`<br/>
> **Output**: `true`<br/>

#### Constraints:

- `1 <= s.length <= 5 * 104`
- `t.length == s.length`
- `s and t consist of any valid ascii character.`

## Solutions

### Solution 1

```java
public boolean isIsomorphic(String s, String t) {
    Map<Integer, Integer> stMap = new HashMap<>();
    char[] sChars = s.toCharArray();
    char[] tChars = t.toCharArray();
    for (int i = 0; i < s.length(); i++) {
        if (!stMap.containsKey((int) sChars[i]) && !stMap.containsValue((int) tChars[i])) {
            stMap.put((int) sChars[i], (int) tChars[i]);
        }
        if (stMap.get((int) sChars[i]) == null || stMap.get((int) sChars[i]) != t.charAt(i)) {
            return false;
        }
    }
    return true;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the length of the input strings `s` and `t`.
    - This is because we iterate through both strings once to check if they are **isomorphic**.
- `Space Complexity`: O(n)
    - Though it looks like O(n), since there can be only 128 (ASCII characters) valid mappings, the complexity can be O(128) or O(1). 

### Solution 2

```java
public boolean isIsomorphic(String s, String t) {
    int[] sIndexes = new int[128];
    int[] tIndexes = new int[128];
    for (int i = 0; i < s.length(); i++) {
        sIndexes[s.charAt(i)] = i;
        tIndexes[t.charAt(i)] = i;
    }
    for (int i = 0; i < s.length(); i++) {
        if (sIndexes[s.charAt(i)] != tIndexes[t.charAt(i)]) {
            return false;
        }
    }
    return true;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the length of the input strings `s` and `t`.
    - This is because we iterate through both strings once to populate the index arrays and then iterate through them again to compare the mappings.
- `Space Complexity`: O(1)
    - This is because we are using fixed-size arrays of length 128 to store the mappings for each character in the ASCII character set.

> [!TIP]
> Both `Solution 1` and `Solution 2` have similar complexities, but the `Solution 2` is faster due to the access to `int[]` arrays is faster than the `Map`.
