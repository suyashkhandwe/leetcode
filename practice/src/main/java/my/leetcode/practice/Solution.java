package my.leetcode.practice;

import com.sun.jdi.request.StepRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] sIndexes = new int[128];
        int[] tIndexes = new int[128];
        for (int i = 0; i < s.length(); i++) {
            sIndexes[s.charAt(i)] = i;
            tIndexes[t.charAt(i)] = i;
        }
        for (int i = 0; i < s.length(); i++) {
            if (sIndexes[s.charAt(i)] != tIndexes[t.charAt(i)]) {
                return false;
            }
        }
        return true;
    }
}