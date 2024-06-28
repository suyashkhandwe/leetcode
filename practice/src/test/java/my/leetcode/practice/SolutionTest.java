package my.leetcode.practice;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    private static final Solution SOLUTION = new Solution();

    public static Stream<Arguments> testSolutionArgs() {
        return Stream.of(
                Arguments.of(new int[]{2, 7, 11, 15}, 9, new int[]{1, 2}),
                Arguments.of(new int[]{2, 7, 11, 15}, 17, new int[]{1, 4}),
                Arguments.of(new int[]{2, 7, 11, 15}, 18, new int[]{2, 3}),
                Arguments.of(new int[]{2, 3, 4}, 6, new int[]{1, 3}),
                Arguments.of(new int[]{2, 3, 4}, 7, new int[]{2, 3}),
                Arguments.of(new int[]{-1, 0}, -1, new int[]{1, 2}),
                Arguments.of(new int[]{-10, -8, -2, 1, 2, 5, 6}, 0, new int[]{3, 5})

        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(int[] numbers, int target, int[] expected) {
        assertArrayEquals(expected, SOLUTION.twoSum(numbers, target));
    }
}
