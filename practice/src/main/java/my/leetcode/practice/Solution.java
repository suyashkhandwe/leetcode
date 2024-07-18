package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer prevIdx = indexMap.put(nums[i], i);
            if (prevIdx != null && prevIdx - i <= k) {
                return true;
            }
        }
        return false;
    }
}
