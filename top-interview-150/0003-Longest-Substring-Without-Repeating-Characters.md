# Problem 3. Longest Substring Without Repeating Characters

> [!NOTE]
> [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/?envType=study-plan-v2&envId=top-interview-150)

Given a string s, find the length of the longest `substring` without repeating characters

> A `substring` is a contiguous non-empty sequence of characters within a string.


### Examples

#### Example 1:

> **Input**: `s = "abcabcbb"`<br/>
> **Output**: `3`<br/>
> **Explanation**: `The answer is "abc", with the length of 3.`

#### Example 2:

> **Input**: `s = "bbbbb"`<br/>
> **Output**: `1`<br/>
> **Explanation**: `The answer is "b", with the length of 1.`

#### Example 3:

> **Input**: `s = "pwwkew"`<br/>
> **Output**: `3`<br/>
> **Explanation**: `The answer is "wke", with the length of 3`.<br/>
> `Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.`

#### Constraints:

- `0 <= s.length <= 5 * 104`
- `s consists of English letters, digits, symbols and spaces.`

## Solutions

### Solution 1

```java
public int lengthOfLongestSubstring(String s) {
    int maxLength = 0;
    char[] chars = s.toCharArray();
    Map<Character, Boolean> tracker = new HashMap<>();
    int l = 0;
    int r = 0;
    while (r < chars.length) {
        if (tracker.containsKey(chars[r])) {
            l++;
            r = l;
            tracker.clear();
            continue;
        }
        tracker.put(chars[r++], true);
        maxLength = Math.max(maxLength, r - l);
    }
    return maxLength;
}
```

#### Complexities

- `Time Complexity`: O(n)
  - `n` is the length of the input string.
  - we iterate through the string only once using two pointers (`l` and `r`)
- `Space Complexity`: O(min(n, m))
    - `n` is the length of the input string
    - `m` is the size of the character set (in this case, 128 for all ASCII characters)
    - Effectively, this becomes constant space to O(1).

### Solution 2

```java
public int lengthOfLongestSubstring(String s) {
    int maxLength = 0;
    char[] chars = s.toCharArray();
    List<Character> tracker = new ArrayList<>();
    int l = 0;
    int r = 0;
    while (r < chars.length) {
        if (tracker.contains(chars[r])) {
            l++;
            r = l;
            tracker.clear();
            continue;
        }
        tracker.add(chars[r++]);
        maxLength = Math.max(maxLength, r - l);
    }
    return maxLength;
}
```

#### Complexities

- `Time Complexity`: O(n)
  - `n` is the length of the input string.
  - we iterate through the string only once using two pointers (`l` and `r`)
- `Space Complexity`: O(min(n, m))
  - `n` is the length of the input string
  - `m` is the size of the character set (in this case, 128 for all ASCII characters)
  - Effectively, this becomes constant space to O(1).

### Solution 3

```java
public int lengthOfLongestSubstring(String s) {
  int n = s.length(), longest = 0;
  int[] nextIndex = new int[128];

  for (int r = 0, l = 0; r < n; r++) {
    l = Math.max(nextIndex[s.charAt(r)], l);
    longest = Math.max(longest, r - l + 1);
    nextIndex[s.charAt(r)] = r + 1;
  }

  return longest;
}
```

#### Complexities

- `Time Complexity`: O(n)
  - `n` is the length of the input string.
  - we iterate through the string only once using two pointers (`l` and `r`)
- `Space Complexity`: O(1)
  - Constant spaces for 128 ASCII characters.

> [!TIP]
> `Solution 1` and `Solution 2` use more operations while `Solution 3` uses the `char to int` conversion to track the same character details with less computations.
> Effectively, `Solution 3` is significantly faster, even though all solutions have the same complexities.  