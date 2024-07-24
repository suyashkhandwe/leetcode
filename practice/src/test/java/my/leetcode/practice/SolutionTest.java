package my.leetcode.practice;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    private static final Solution SOLUTION = new Solution();

    public static Stream<Arguments> testSolutionArgs() {
        return Stream.of(
                Arguments.of(new int[][]{{1, 3}, {6, 9}},
                        new int[]{2, 5},
                        new int[][]{{1, 5}, {6, 9}}),
                Arguments.of(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}},
                        new int[]{4, 8},
                        new int[][]{{1, 2}, {3, 10}, {12, 16}}),
                Arguments.of(new int[][]{},
                        new int[]{5, 7},
                        new int[][]{{5, 7}}),
                Arguments.of(new int[][]{{1, 5}},
                        new int[]{2, 7},
                        new int[][]{{1, 7}})
        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(int[][] intervals, int[] newInterval, int[][] expected) {
        int[][] actual = SOLUTION.insert(intervals, newInterval);
        assertArrayEquals(expected, actual);
    }
}
