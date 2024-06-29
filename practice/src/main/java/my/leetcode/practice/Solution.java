package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            if (height[r] >= height[l]) {
                maxArea = Math.max(maxArea, height[l] * (r - l++));
            } else {
                maxArea = Math.max(maxArea, height[r] * (r-- - l));
            }
        }
        return maxArea;
    }
}