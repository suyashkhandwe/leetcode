package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Integer> frequencies = new HashSet<>();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') {
                    int number = Character.getNumericValue(board[r][c]);
                    int rTracker = 100 + r * 10 + number;
                    int cTracker = 1000 + c * 100 + number;
                    int bTracker = 10000 + 1000 * (r / 3 + c / 3) + number;
                    if (!frequencies.add(100 + r * 10 + number) ||
                            !frequencies.add(1000 + c * 100 + number) ||
                            !frequencies.add(10000 + 1000 * (r / 3 + c / 3) + number)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}