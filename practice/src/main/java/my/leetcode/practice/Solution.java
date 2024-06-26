package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Solution {
    public String convert(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }

        int nonDiagonalDelta = 2 * (numRows - 1);
        StringBuilder convertedBuilder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int sIdx = i;
            while (sIdx < s.length()) {
                convertedBuilder.append(s.charAt(sIdx));
                if (i != 0 && i != numRows - 1) {
                    int prevDiagonalSIdx = sIdx + nonDiagonalDelta - (2 * i);
                    if (prevDiagonalSIdx < s.length()) {
                        convertedBuilder.append(s.charAt(prevDiagonalSIdx));
                    }
                }
                sIdx += nonDiagonalDelta;
            }
        }
        return convertedBuilder.toString();
    }
}
/**
 * 0,0          0,3         0,6
 * 1,0      1,2 1,3     1,5 1,6
 * 2,0  2,1     2,3 2,4
 * 3,0          3,3
 */

// A    B   C   D   E   F   G   H   I   J   K   L   M   N
// 00   01  02  03  04  05  06  07  08  09  10  11  12  13

// A    E   I   M   B   D   F   H   J   L   N   C   G   K
// 00   04  08  12  01  03  05  07  09  11  13  02  06  10 (3)
// i0   04  04  04  i1 (-2) 04 (-2) 04 (-2) 04  i2  04  04
// no -2 on i =0 or i = 2

// A    G   M   B   F   H   L   N   C   E   I   K   D   J
// 00   06  12  01  05  07  11  13  02  04  08  10  03  09 (4)
// i0   +6  +6  i1 (-2) +6 (-2) +6  i2 (-4) +6  ??  i2  +6

// A    M   B   L   N   C   K   D   J   E   I   F   H   G
// 00   12  01  11  13  02  10  03  09  04  08  05  07  06 (7)
// i0   +12 i1  -2  +12 i2  -4  i3  -5  i4  -8  i5  -10 i5