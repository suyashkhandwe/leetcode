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
                                new int[]{1, 1, 1},
                                new int[]{1, 0, 1},
                                new int[]{1, 1, 1}},
                        new int[][]{
                                new int[]{1, 0, 1},
                                new int[]{0, 0, 0},
                                new int[]{1, 0, 1}}),
                Arguments.of(new int[][]{
                                new int[]{0, 1, 2, 0},
                                new int[]{3, 4, 5, 2},
                                new int[]{1, 3, 1, 5}},
                        new int[][]{
                                new int[]{0, 0, 0, 0},
                                new int[]{0, 4, 5, 0},
                                new int[]{0, 3, 1, 0}})

        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(int[][] matrix, int[][] expected) {
        SOLUTION.setZeroes(matrix);
        assertArrayEquals(expected, matrix);
    }
}
