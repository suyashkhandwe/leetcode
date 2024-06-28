package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        int sum = numbers[l] + numbers[r];
        // Since we have a guaranteed sum = target.
        while (sum != target) {
            if (sum > target) {
                r--;
            } else {
                l++;
            }
            sum = numbers[l] + numbers[r];
        }
        return new int[]{l + 1, r + 1};
    }
}