package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> triplets = new HashSet<>();
        Arrays.sort(nums);
        if (nums[0] > 0) {
            return List.of();
        }
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            int smallestNumIdx = i + 1;
            int largestNumIdx = len - 1;
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            while (smallestNumIdx < largestNumIdx) {
                int sum = nums[i] + nums[smallestNumIdx] + nums[largestNumIdx];
                if (sum == 0) {
                    triplets.add(List.of(nums[i], nums[smallestNumIdx], nums[largestNumIdx]));
                    while (smallestNumIdx < largestNumIdx && nums[smallestNumIdx] == nums[smallestNumIdx + 1]) {
                        smallestNumIdx++;
                    }
                    while (smallestNumIdx < largestNumIdx && nums[largestNumIdx] == nums[largestNumIdx - 1]) {
                        largestNumIdx--;
                    }
                    smallestNumIdx++;
                    largestNumIdx--;
                } else if (sum > 0) {
                    largestNumIdx--;
                } else {
                    smallestNumIdx++;
                }
            }
        }
        return new ArrayList<>(triplets);
    }
}

//-4, -1, -1, 0, 1, 2