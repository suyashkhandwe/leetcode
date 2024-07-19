package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import javax.swing.plaf.IconUIResource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> setOfNums = Arrays.stream(nums).boxed().collect(Collectors.toSet());

        if (setOfNums.size() < 2) {
            return setOfNums.size();
        }

        List<Integer> smallestNums = Arrays.stream(nums)
                .boxed()
                .filter(num -> setOfNums.contains(num + 1))
                .filter(num -> !setOfNums.contains(num - 1))
                .toList();

        int longestConsecutiveLength = 1;
        int consecutiveLength = 1;
        for (Integer smallestNum : smallestNums) {
            while (setOfNums.contains(++smallestNum)) {
                consecutiveLength++;
            }
            longestConsecutiveLength = Math.max(consecutiveLength, longestConsecutiveLength);
            consecutiveLength = 1;
        }
        return longestConsecutiveLength;
    }
}
