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
                Arguments.of("abba", "dog cat cat dog", true),
                Arguments.of("abba", "dog cat cat fish", false),
                Arguments.of("aaaa", "dog cat cat dog", false),
                Arguments.of("abba", "dog dog dog dog", false)
        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(String pattern, String s, boolean expected) {
        boolean actual = SOLUTION.wordPattern(pattern, s);
        assertEquals(expected, actual);
    }
}
