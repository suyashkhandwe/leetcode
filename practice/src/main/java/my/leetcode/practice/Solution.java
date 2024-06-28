package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

@Slf4j
class Solution {

    public boolean isPalindrome(String s) {
        char[] chars = s.toLowerCase(Locale.ROOT).toCharArray();
        int l = 0;
        int r = s.length() - 1;
        while (l <= r) {
            if (!isAlphaNumeric(chars[l])) {
                l++;
            } else if (!isAlphaNumeric(chars[r])) {
                r--;
            } else if (chars[l] != chars[r]) {
                return false;
            } else {
                l++;
                r--;
            }
        }
        return true;
    }

    private boolean isAlphaNumeric(int asciiChar) {
        return (asciiChar >= 'a' && asciiChar <= 'z')
                || (asciiChar >= '0' && asciiChar <= '9');
    }
}