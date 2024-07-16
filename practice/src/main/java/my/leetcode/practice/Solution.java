package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] characterFrequencies = new int[26];
        for (char c : magazine.toCharArray()) {
            characterFrequencies[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (characterFrequencies[c - 'a'] == 0) {
                return false;
            }
            characterFrequencies[c - 'a']--;
        }
        return true;
    }
}