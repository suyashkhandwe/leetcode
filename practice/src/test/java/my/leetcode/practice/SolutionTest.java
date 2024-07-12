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
                Arguments.of("barfoothefoobarman",
                        new String[]{"foo", "bar"},
                        List.of(0, 9)
                ),
                Arguments.of("wordgoodgoodgoodbestword",
                        new String[]{"word", "good", "best", "word"},
                        List.of()
                ),
                Arguments.of("barfoofoobarthefoobarman",
                        new String[]{"bar", "foo", "the"},
                        List.of(6, 9, 12)
                ),
                Arguments.of("wordgoodgoodgoodbestword",
                        new String[]{"word", "good", "best", "good"},
                        List.of(8)
                ),
                Arguments.of("lingmindraboofooowingdingbarrwingmonkeypoundcake",
                        new String[]{"fooo", "barr", "wing", "ding", "wing"},
                        List.of(13)
                ),
                Arguments.of("ababaab",
                        new String[]{"ab", "ba", "ba"},
                        List.of(1)
                ),
                Arguments.of("a".repeat(10000),
                        "a-".repeat(5000).split("-"),
                        IntStream.rangeClosed(0, 5000)
                                .boxed()
                                .sorted()
                                .collect(Collectors.toList())
                ),
                Arguments.of("ab".repeat(5000),
                        new String[]{"ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba"},
                        List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(String s, String[] words, List<Integer> expected) {
        List<Integer> actual = SOLUTION.findSubstring(s, words);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
