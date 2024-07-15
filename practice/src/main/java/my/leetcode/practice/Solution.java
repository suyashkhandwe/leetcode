package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean doesFirstRoweHasZero = false;
        boolean doesFirstColHasZero = false;

        //1. check if the first row has a zero
        for (int c = 0; c < n && !doesFirstRoweHasZero; c++) {
            doesFirstRoweHasZero = matrix[0][c] == 0;
        }

        //2. check if the first column has a zero
        for (int r = 0; r < m && !doesFirstColHasZero; r++) {
            doesFirstColHasZero = matrix[r][0] == 0;
        }

        //3. iterate through the rest (starting at 1,1) of the matrix
        // and if [r,c] = 0, then set the 0th c and 0th r as zero.
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (matrix[r][c] == 0) {
                    matrix[r][0] = 0;
                    matrix[0][c] = 0;
                }
            }
        }

        //4. set the zeros in the matrix (starting at 1,1) if the first row or column has a zero
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (matrix[r][0] == 0 || matrix[0][c] == 0) {
                    matrix[r][c] = 0;
                }
            }
        }

        //5. set the zeros in the first row if the flag was set above
        for (int c = 0; c < n && doesFirstRoweHasZero; c++) {
            matrix[0][c] = 0;
        }

        //6. set the zeros in the first col if the flag was set above
        for (int r = 0; r < m && doesFirstColHasZero; r++) {
            matrix[r][0] = 0;
        }
    }
}
