package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                log.info("num[{}]={} num[{}]={} sum={}", j - i, nums[j - i], j, nums[j], nums[j] + nums[j - i]);
                if (nums[j] + nums[j - i] == target) {
                    log.info("Returns [{}, {}]", j - i, j);
                }
            }
        }
        log.info("-----------");

        int[] indexes = new int[2];
        for (int i = 0; i < nums.length; i++) {
            indexes[0] = i;
            for (int j = i + 1; j < nums.length; j++) {
                log.info("num[{}]={} num[{}]={} sum={}", i, nums[i], j, nums[j], nums[i] + nums[j]);
                if (nums[i] + nums[j] == target) {
                    indexes[1] = j;
                    return indexes;
                }
            }
        }


        return indexes;
    }
}
