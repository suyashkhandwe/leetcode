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
                Arguments.of("sadbutsad", "sad", 0),
                Arguments.of("leetcode", "leeto", -1),
                Arguments.of("mississippi", "issipi", -1),
                Arguments.of("a", "a", 0)

        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(final String haystack, final String needle, final int expected) {
        assertEquals(expected, SOLUTION.strStr(haystack, needle));
    }
}
