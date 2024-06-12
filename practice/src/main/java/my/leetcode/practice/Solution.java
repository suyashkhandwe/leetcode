package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.Arrays;

@Slf4j
class Solution {

    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        int higherRatingFromLeft = 0;
        int higherRatingFromRight = 0;
        int minCandies = 0;
        for (int l = 0, r = ratings.length - 1; l < ratings.length - 1; l++, r--) {
            if (ratings[l + 1] > ratings[l]) {
                higherRatingFromLeft++;
                minCandies += higherRatingFromLeft;
            }
            if (ratings[r] > ratings[r - 1]) {
                higherRatingFromRight++;
            }
        }
        return minCandies + higherRatingFromLeft + higherRatingFromRight + ratings.length;
    }
}
// r = [1, 2, 87, 87, 87, 2, 1]
// ac = [1, 2, 3, 1, 2, 2, 1]
// ec = [1, 2, 3, 1, 3, 2, 1]