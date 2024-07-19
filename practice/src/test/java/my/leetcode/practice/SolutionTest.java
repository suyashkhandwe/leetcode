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
                Arguments.of(new int[]{100, 4, 200, 1, 3, 2}, 4),
                Arguments.of(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}, 9),
                Arguments.of(new int[]{}, 0),
                Arguments.of(new int[]{0}, 1),
                Arguments.of(new int[]{-6, -1, -1, 9, -8, -6, -6, 4, 4, -3, -8, -1}, 1),
                Arguments.of(new int[]{1, 3, 5, 2, 4}, 5),
                Arguments.of(new int[]{9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6}, 7),
                Arguments.of(new int[]{9, 1, -3, 2, 4, 8, 3, -1, 6, -2, -4, 7}, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(int[] nums, int expected) {
        int actual = SOLUTION.longestConsecutive(nums);
        assertEquals(expected, actual);
    }
}
