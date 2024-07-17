package my.leetcode.practice;

import com.sun.jdi.request.StepRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] sStrings = s.split(" ");
        if (pattern.length() != sStrings.length) {
            return false;
        }

        String[] sStringsMap = new String[26];
        int i = -1;
        while (++i < pattern.length()) {
            if (sStringsMap[pattern.charAt(i) - 97] == null) {
                for (String string : sStringsMap) {
                    if (sStrings[i].equals(string)) {
                        return false;
                    }
                }
                sStringsMap[pattern.charAt(i) - 97] = sStrings[i];
            }
            if (!sStringsMap[pattern.charAt(i) - 97].equals(sStrings[i])) {
                return false;
            }
        }
        return true;
    }
}
