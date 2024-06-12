package my.leetcode.practice;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    private static final Solution SOLUTION = new Solution();

    public static Stream<Arguments> testSolutionArgs() {
        return Stream.of(
                Arguments.of(new int[]{1, 0, 2},
                        5),
                Arguments.of(new int[]{1, 2, 2},
                        4),
                Arguments.of(new int[]{1, 1, 1},
                        3),
                Arguments.of(new int[]{10, 5, 10, 10, 5},
                        8),
                Arguments.of(new int[]{1, 3, 2, 2, 1},
                        7),
                Arguments.of(new int[]{1, 2, 87, 87, 87, 2, 1},
                        13),
                Arguments.of(new int[]{1,3,4,5,2},
                        11)

        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(final int[] ratings, final int expected) {
        int actual = SOLUTION.candy(ratings);
        assertEquals(expected, actual);
    }
}
