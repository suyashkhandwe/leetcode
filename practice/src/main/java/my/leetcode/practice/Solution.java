package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Slf4j
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing(it -> it[0]));

        int[][] mergedIntervals = new int[intervals.length][2];
        mergedIntervals[0] = intervals[0];
        int j = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (mergedIntervals[j][1] >= intervals[i][0]) {
                mergedIntervals[j][1] = Math.max(mergedIntervals[j][1], intervals[i][1]);
            } else {
                mergedIntervals[++j] = intervals[i];
            }
        }
        return Arrays.copyOfRange(mergedIntervals, 0, j + 1);
    }
}
