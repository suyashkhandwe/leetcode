# Problem 242. Valid Anagram

> [!NOTE]
> [242. Valid Anagram](https://leetcode.com/problems/valid-anagram/description/?envType=study-plan-v2&envId=top-interview-150)

Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`, and `false` otherwise.

An **Anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

**Follow up**: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?

### Examples

#### Example 1:

> **Input**: `s = "anagram", t = "nagaram"`<br/>
> **Output**: `true`<br/>

#### Example 2:

> **Input**: `s = "rat", t = "car"`<br/>
> **Output**: `false`<br/>

#### Constraints:

- `1 <= s.length, t.length <= 5 * 104`
- `s and t consist of lowercase English letters.`

## Solutions

### Solution 1

```java
public boolean isAnagram(String s, String t) {
    if (s == null || t == null || s.length() != t.length()) {
        return false;
    }
    Map<Character, Integer> sFrequencies = new HashMap<>();
    Map<Character, Integer> tFrequencies = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
        sFrequencies.put(s.charAt(i), sFrequencies.getOrDefault(s.charAt(i), 0) + 1);
        tFrequencies.put(t.charAt(i), tFrequencies.getOrDefault(t.charAt(i), 0) + 1);
    }
    return sFrequencies.size() == tFrequencies.size() && sFrequencies.equals(tFrequencies);
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the length of the input strings `s` and `t`.
    - This is because we iterate through both strings once to calculate the frequencies of each character.
- `Space Complexity`: O(n)
    - This is because we use two `HashMaps` to store the frequencies of characters in both strings.
    - The space complexity could be considered O(1) if we assume a fixed set of characters (e.g., ASCII characters), but in general, it is O(n) to account for all possible characters.

### Solution 2

```java
public boolean isAnagram(String s, String t) {
    int[] charCounter = new int[26];
    for (int i = 0; i < s.length(); i++) {
        charCounter[s.charAt(i) - 97]++;
    }
    for (int i = 0; i < t.length(); i++) {
        charCounter[t.charAt(i) - 97]--;
        if (charCounter[t.charAt(i) - 97] < 0) {
            return false;
        }
    }
    for (int charCount : charCounter) {
        if (charCount != 0) {
            return false;
        }
    }
    return true;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the length of the input strings `s` and `t`.
    - This is because we iterate through both strings once to populate the `charCounter` array and then iterate through it again to check for any non-zero values.
- `Space Complexity`: O(1)
    - This is because the size of the `charCounter` array is fixed at `26` (for the 26 lowercase letters of the alphabet) regardless of the input size.
