# Problem 76. Minimum Window Substring

> [!NOTE]
> [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/description/?envType=study-plan-v2&envId=top-interview-150)

Given two strings `s` and `t` of lengths `m` and `n` respectively, return the minimum window **substring** of `s` such that every character in `t` (including duplicates) is included in the window.
If there is no such substring, return the empty string `""`.

The testcases will be generated such that the answer is unique.

### Examples

#### Example 1:

> **Input**: `s = "ADOBECODEBANC", t = "ABC"`<br/>
> **Output**: `"BANC"`<br/>
> **Explanation**: `The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.`

#### Example 2:

> **Input**: `s = "a", t = "a"`<br/>
> **Output**: `"a"`<br/>
> **Explanation**: `The entire string s is the minimum window.`

#### Example 3:

> **Input**: `s = "a", t = "aa"`<br/>
> **Output**: `""`<br/>
> **Explanation**: `Both 'a's from t must be included in the window. Since the largest window of s only has one 'a', return empty string.`

#### Constraints:

- `m == s.length`
- `n == t.length`
- `1 <= m, n <= 105`
- `s and t consist of uppercase and lowercase English letters.`

## Solutions

### Solution 1

```java
public String minWindow(String s, String t) {
    if (t.length() == 1 && t.length() == s.length() && s.equals(t)) {
        return t;
    } else if (t.length() > s.length()) {
        return "";
    } else {
        int[] tFrequency = new int[128];
        for (int i = 0; i < t.length(); i++) {
            tFrequency[t.charAt(i)]++;
        }
        int windowSize = t.length();
        int sLen = s.length();
        int startPosition = 0;
        while (windowSize <= sLen) {
            int[] sFrequency = new int[128];
            for (int sIdx = startPosition; sIdx < startPosition + windowSize; sIdx++) {
                if (tFrequency[s.charAt(sIdx)] > 0 && sFrequency[s.charAt(sIdx)] < tFrequency[s.charAt(sIdx)]) {
                    sFrequency[s.charAt(sIdx)]++;
                }
            }
            if (Arrays.equals(tFrequency, sFrequency)) {
                int endPosition = startPosition + windowSize;
                return s.substring(startPosition, endPosition);
            } else if (startPosition < sLen - windowSize) {
                startPosition++;
            } else {
                windowSize++;
                startPosition = 0;
            }
        }
        return "";
    }
}
```

#### Complexities

- `Time Complexity`: O(n^2)
    - iterates through the string `s` with a window size that increases by 1 each iteration
    - there is another nested loop that iterates through the window of `s`
- `Space Complexity`: O(1)
    - using constant amount of space

### Solution 2

```java
public String minWindow(String s, String t) {
    int sLen = s.length();
    int tLen = t.length();
    if (sLen == 1 && tLen == 1) {
        return s.equals(t) ? s : "";
    } else if (tLen > sLen) {
        return "";
    } else {
        // 1. get the frequency of each character in t
        // 2. use ASCII codes for characters as index
        int[] tFrequency = new int[128];
        for (int i = 0; i < tLen; i++) {
            tFrequency[t.charAt(i)]++;
        }

        int tCounter = t.length();
        // 3. use sliding window of l & r both starting from 0
        int l = 0;
        int r = 0;
        // 4. set starting index for the window to 0 and
        // set minWindow to the length of s+1 as this is potentially an invalid window
        int startIdx = 0;
        int minWindow = sLen + 1;
        // 5. iterate through the string moving the r to the right
        while (r < sLen) {
            // 6. if the character at r is in tFrequency, decrement the tCounter
            // and also decrement the frequency of the character at r
            tCounter -= tFrequency[s.charAt(r)] > 0 ? 1 : 0;
            tFrequency[s.charAt(r)]--;
            // 7. Keep moving right using  the r pointer to expand the window
            r++;

            // 8. if the tCounter is 0, we have found a potential window,
            // now we need to move the l pointer to find the smallest window
            while (tCounter == 0) {
                // 9. if the current window is smaller than minWindow,
                // update minWindow and startIdx
                if (r - l < minWindow) {
                    startIdx = l;
                    minWindow = r - l;
                }

                // 10. if the character at l is in tFrequency, increment the tCounter
                // and also increment the frequency of the character at l
                // TIP - The index in tFrequency is the ASCII code of the character and
                // would be negative if the character is not in t
                tCounter += tFrequency[s.charAt(l)] == 0 ? 1 : 0;
                tFrequency[s.charAt(l)]++;

                // 11. Keep moving right using the l pointer to compress the window
                l++;
            }
        }
        // 12. return the substring between startIdx and startIdx + minWindow
        return minWindow == sLen + 1 ? "" : s.substring(startIdx, startIdx + minWindow);
    }
}
```

#### Complexities

- `Time Complexity`: O(m+n)
    - iterates through the `m` and `n` elements of `s` and `t`
- `Space Complexity`: O(1)
    - uses constant space regardless of the input size
