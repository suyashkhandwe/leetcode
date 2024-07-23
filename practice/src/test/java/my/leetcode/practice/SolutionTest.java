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
                Arguments.of(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}},
                        new int[][]{{1, 6}, {8, 10}, {15, 18}}),
                Arguments.of(new int[][]{{1, 4}, {4, 5}},
                        new int[][]{{1, 5}}),
                Arguments.of(new int[][]{{2, 3}, {5, 5}, {2, 2}, {3, 4}, {3, 4}},
                        new int[][]{{2, 4}, {5, 5}}),
                Arguments.of(new int[][]{{2, 3}, {2, 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2}, {4, 6}},
                        new int[][]{{1, 3}, {4, 7}}),
                Arguments.of(new int[][]{{1, 4}, {0, 1}},
                        new int[][]{{0, 4}}),
                Arguments.of(new int[][]{{1, 4}, {0, 4}},
                        new int[][]{{0, 4}}),
                Arguments.of(new int[][]{{1, 4}, {0, 2}},
                        new int[][]{{0, 4}}),
                Arguments.of(new int[][]{{1, 4}, {0, 0}},
                        new int[][]{{0, 0}, {1, 4}}),
                Arguments.of(new int[][]{{1, 4}},
                        new int[][]{{1, 4}})
        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(int[][] intervals, int[][] expected) {
        int[][] actual = SOLUTION.merge(intervals);
        assertArrayEquals(expected, actual);
    }
}
