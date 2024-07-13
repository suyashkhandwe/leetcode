package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Solution {
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
}