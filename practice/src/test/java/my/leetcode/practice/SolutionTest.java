package my.leetcode.practice;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    private static final Solution SOLUTION = new Solution();

    public static Stream<Arguments> testSolutionArgs() {
        return Stream.of(
                Arguments.of(new int[]{-1, 0, 1, 2, -1, -4},
                        List.of(
                                List.of(-1, -1, 2),
                                List.of(-1, 0, 1)
                        )),
                Arguments.of(new int[]{0, 1, 1},
                        List.of()),
                Arguments.of(new int[]{0, 0, 0},
                        List.of(
                                List.of(0, 0, 0)
                        )),
                Arguments.of(new int[]{-1, 0, 1, 0},
                        List.of(
                                List.of(-1, 0, 1)
                        )),
                Arguments.of(new int[]{3, 0, -2, -1, 1, 2},
                        List.of(
                                List.of(-2, -1, 3),
                                List.of(-2, 0, 2),
                                List.of(-1, 0, 1)
                        )),
                Arguments.of(new int[]{9,-4,-5,8,-5,7,5,-6,-4,-13,9,-10,-13,-6,2,-15,-13,-9,-4,-13,-9,-9,13,-13,-9,9,-15,1,0,-14,-8,-13,-11,-5,2,0,9,14,9,-9,8,-5,-12,10,-3,5,3,-1,12,14,1,10,12,-1,13,-12,-14,13,4,-7,6,4,-5,11,6,4,-12,0,3,4,-2,-3,7,1,14,-11,-8,2,-5,11,-7,3,6,-9,9,4,-14,10,-6,-2,-11,-14,-13,-9,4,0,11,-1,-15,-9,-12,-1,3,10,7,-5,6,6,12,8,2,-9,-4,-6,-11,-9,5,-10,-14,-15,3},
                        List.of(
                                List.of(-2, -1, 3),
                                List.of(-2, 0, 2),
                                List.of(-1, 0, 1)
                        ))
//                ,
//                Arguments.of(
//                        IntStream.rangeClosed(3, 3000)
//                                .map(it -> new Random().nextInt(-10000, 10000))
//                                .toArray(),
//                        List.of(
//                                List.of(0, 0, 0)
//                        ))
        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(int[] nums, List<List<Integer>> expected) {
        List<List<Integer>> actual = SOLUTION.threeSum(nums);
//        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
        for (List<Integer> e : expected) {
            for (List<Integer> a : actual) {
//                assertEquals(e, a);
            }
        }
    }
}
