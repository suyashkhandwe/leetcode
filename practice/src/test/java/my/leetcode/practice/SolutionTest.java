package my.leetcode.practice;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    private static final Solution SOLUTION = new Solution();

    public static Stream<Arguments> testSolutionArgs() {
        return Stream.of(
                Arguments.of(new char[][]{
                                new char[]{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                                new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                                new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                                new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                                new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                                new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                                new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                                new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                                new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'}},
                        true),
                Arguments.of(new char[][]{
                                new char[]{'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                                new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                                new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                                new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                                new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                                new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                                new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                                new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                                new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'}},
                        false),
                Arguments.of(new char[][]{
                                new char[]{'.', '.', '.', '.', '5', '.', '.', '1', '.'},
                                new char[]{'.', '4', '.', '3', '.', '.', '.', '.', '.'},
                                new char[]{'.', '.', '.', '.', '.', '3', '.', '.', '1'},
                                new char[]{'8', '.', '.', '.', '.', '.', '.', '2', '.'},
                                new char[]{'.', '.', '2', '.', '7', '.', '.', '.', '.'},
                                new char[]{'.', '1', '5', '.', '.', '.', '.', '.', '.'},
                                new char[]{'.', '.', '.', '.', '.', '2', '.', '.', '.'},
                                new char[]{'.', '2', '.', '9', '.', '.', '.', '.', '.'},
                                new char[]{'.', '.', '4', '.', '.', '.', '.', '.', '.'}},
                        false)
        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(char[][] board, boolean expected) {
        boolean actual = SOLUTION.isValidSudoku(board);
        assertEquals(expected, actual);
    }
}
