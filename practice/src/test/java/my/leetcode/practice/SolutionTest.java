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
                Arguments.of("abc", "ahbgdc", true),
                Arguments.of("aec", "ahbgdc", false),
                Arguments.of("axc", "ahbgdc", false),
                Arguments.of("", "ahbgdc", true)
        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(final String s, final String t, final boolean expected) {
        assertEquals(expected, SOLUTION.isSubsequence(s, t));
    }
}
