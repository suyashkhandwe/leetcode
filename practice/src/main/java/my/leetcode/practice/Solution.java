package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        int[][] updatedIntervals = new int[intervals.length + 1][2];
        boolean inserted = false;
        int updatedIntervalsIdx = 0;
        for (int i = 0; i < intervals.length; ) {
            if (!inserted && newInterval[0] <= intervals[i][0]) {
                updatedIntervals[updatedIntervalsIdx++] = newInterval;
                inserted = true;
            } else {
                updatedIntervals[updatedIntervalsIdx++] = intervals[i++];
            }
        }
        if (!inserted) {
            updatedIntervals[updatedIntervalsIdx] = newInterval;
        }


        int[][] mergedIntervals = new int[updatedIntervals.length][2];
        mergedIntervals[0] = updatedIntervals[0];
        int j = 0;
        for (int i = 1; i < updatedIntervals.length; i++) {
            if (mergedIntervals[j][1] >= updatedIntervals[i][0]) {
                mergedIntervals[j][1] = Math.max(mergedIntervals[j][1], updatedIntervals[i][1]);
            } else {
                mergedIntervals[++j] = updatedIntervals[i];
            }
        }
        return Arrays.copyOfRange(mergedIntervals, 0, j + 1);
    }
}
