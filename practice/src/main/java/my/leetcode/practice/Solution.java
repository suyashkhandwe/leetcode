package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
class Solution {
    public boolean isSubsequence(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen < 1) {
            return true;
        }

        int sIdx = 0;
        int tIdx = 0;
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        while (tIdx < tLen && sIdx < sLen) {
            if (sChars[sIdx] == tChars[tIdx++]) {
                sIdx++;
            }
        }
        return sIdx == sLen;
    }
}