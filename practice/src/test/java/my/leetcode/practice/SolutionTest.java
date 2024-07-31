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
                Arguments.of("1 + 1", 2),
                Arguments.of(" 2-1 + 2 ", 3),
                Arguments.of(" (2-1) + 2 ", 3),
                Arguments.of(" -(2-1) + 2 ", 1),
                Arguments.of(" -(1-2) + 2 ", 3),
                Arguments.of(" (1-2) + 2 ", 1),
                Arguments.of(" 2 + -(1-2)", 3),
                Arguments.of("- (3 - (- (4 + 5) ) )", -12),
                Arguments.of("- (3 - (- (4 - 5) ) )", -2),
                Arguments.of("1-( -2)", 3),
                Arguments.of("100 + 10", 110),
                Arguments.of("(1+(4+5+2)-3)+(6+8)",23)
        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(String s, int expected) {
        int actual = SOLUTION.calculate(s);
        assertEquals(expected, actual);

    }
}
