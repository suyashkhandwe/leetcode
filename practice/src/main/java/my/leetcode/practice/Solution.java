package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Solution {
    public void rotate(int[][] matrix) {
        int colEndIdx = matrix.length - 1;
        int rowEndInd = matrix.length / 2;
        int r = 0;
        while (r < rowEndInd) {
            int c = r;
            while (c < colEndIdx) {
                // Save the top left.
                int topLeft = matrix[r][c];
                // Copy bottom left to top left
                matrix[r][c] = matrix[colEndIdx - c + r][r];
                // Copy bottom right to bottom left
                matrix[colEndIdx - c + r][r] = matrix[colEndIdx][colEndIdx - c + r];
                // Copy top right to bottom right
                matrix[colEndIdx][colEndIdx - c + r] = matrix[c][colEndIdx];
                // Copy saved top left to top right
                matrix[c][colEndIdx] = topLeft;
                c++;
            }
            r++;
            colEndIdx--;
        }
    }
}
