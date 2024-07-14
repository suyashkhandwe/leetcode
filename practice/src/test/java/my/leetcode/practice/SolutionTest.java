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
                Arguments.of(new int[][]{
                                new int[]{1, 2, 3},
                                new int[]{4, 5, 6},
                                new int[]{7, 8, 9}},
                        new int[][]{
                                new int[]{7, 4, 1},
                                new int[]{8, 5, 2},
                                new int[]{9, 6, 3}}),
                Arguments.of(new int[][]{
                                new int[]{5, 1, 9, 11},
                                new int[]{2, 4, 8, 10},
                                new int[]{13, 3, 6, 7},
                                new int[]{15, 14, 12, 16}},
                        new int[][]{
                                new int[]{15, 13, 2, 5},
                                new int[]{14, 3, 4, 1},
                                new int[]{12, 6, 8, 9},
                                new int[]{16, 7, 10, 11}})

        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(int[][] matrix, int[][] expected) {
        SOLUTION.rotate(matrix);
        assertArrayEquals(expected, matrix);
    }
}
