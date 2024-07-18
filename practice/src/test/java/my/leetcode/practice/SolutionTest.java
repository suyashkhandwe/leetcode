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
                Arguments.of(new int[]{1, 2, 3, 1}, 3, true),
                Arguments.of(new int[]{1, 0, 1, 1}, 1, true),
                Arguments.of(new int[]{1, 1}, 1, true),
                Arguments.of(new int[]{1, 2, 3, 1, 2, 3}, 2, false)
        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(int[] nums, int k, boolean expected) {
        boolean actual = SOLUTION.containsNearbyDuplicate(nums, k);
        assertEquals(expected, actual);
    }
}
