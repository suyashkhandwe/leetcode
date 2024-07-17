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
                Arguments.of("anagram", "nagaram", true),
                Arguments.of("rat", "car", false)
        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(String s, String t, boolean expected) {
        boolean actual = SOLUTION.isAnagram(s, t);
        assertEquals(expected, actual);
    }
}
