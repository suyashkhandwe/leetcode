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
                Arguments.of("PAYPALISHIRING", 3, "PAHNAPLSIIGYIR"),
                Arguments.of("PAYPALISHIRING", 4, "PINALSIGYAHRPI"),
                Arguments.of("ABCDEFGHIJKLMN", 3, "AEIMBDFHJLNCGK"),
                Arguments.of("ABCDEFGHIJKLMN", 4, "AGMBFHLNCEIKDJ"),
                Arguments.of("ABCDEFGHIJKLMN", 7, "AMBLNCKDJEIFHG"),
                Arguments.of("A", 1, "A"),
                Arguments.of("AB", 1, "AB"),
                Arguments.of("AB", 2, "AB"),
                Arguments.of("ABCD", 3, "ABDC")


        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(final String s, int numRows, final String expected) {
        String actual = SOLUTION.convert(s, numRows);
        assertEquals(expected, actual);
    }
}
