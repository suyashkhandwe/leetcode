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
                Arguments.of("egg", "add", true),
                Arguments.of("foo", "bar", false),
                Arguments.of("paper", "title", true),
                Arguments.of("ab", "aa", false),
                Arguments.of("badc", "baba", false)
        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(String s, String t, boolean expected) {
        boolean actual = SOLUTION.isIsomorphic(s, t);
        assertEquals(expected, actual);
    }
}
