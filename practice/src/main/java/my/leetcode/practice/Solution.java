package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Solution {
    public int strStr(String haystack, String needle) {
        int returnIdx = -1;
        int hIdx = 0;
        char[] haystackChars = haystack.toCharArray();
        char[] needleChars = needle.toCharArray();
        int haystackLen = haystack.length();
        int needleLen = needle.length();
        while (hIdx <= haystackLen - needleLen) {
            returnIdx = hIdx;
            for (int nIdx = 0; nIdx < needleLen; nIdx++) {
                if (haystackChars[hIdx + nIdx] != needleChars[nIdx]) {
                    returnIdx = -1;
                    break;
                }
            }
            if (returnIdx > -1) {
                return returnIdx;
            }
            hIdx++;
        }
        return returnIdx;
    }
}
