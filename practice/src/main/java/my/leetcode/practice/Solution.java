package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] charCounter = new int[26];

        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            charCounter[sCharArray[i] - 'a']++;
            charCounter[tCharArray[i] - 'a']--;
        }
        for (int charCount : charCounter) {
            if (charCount != 0) {
                return false;
            }
        }
        return true;
    }
}
