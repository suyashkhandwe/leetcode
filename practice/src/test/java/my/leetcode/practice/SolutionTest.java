package my.leetcode.practice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SolutionTest {
    private static final Solution SOLUTION = new Solution();

    public static Stream<Arguments> testSolutionArgs() {

        Solution.ListNode head = new Solution.ListNode(1);
        head.next = new Solution.ListNode(2);
        head.next.next = new Solution.ListNode(3);
        head.next.next.next = new Solution.ListNode(4);
        head.next.next.next.next = new Solution.ListNode(5);

        Solution.ListNode reversed = new Solution.ListNode(1);
        reversed.next = new Solution.ListNode(4);
        reversed.next.next = new Solution.ListNode(3);
        reversed.next.next.next = new Solution.ListNode(2);
        reversed.next.next.next.next = new Solution.ListNode(5);

        return Stream.of(
                Arguments.of(head, reversed)
        );
    }

    @ParameterizedTest
    @MethodSource("testSolutionArgs")
    void testSolution(Solution.ListNode head, Solution.ListNode expected) {
        Solution.ListNode actual = SOLUTION.reverseBetween(head, 2, 4);
        while (expected != null) {
            assertEquals(expected.val, actual.val);
            actual = actual.next;
            expected = expected.next;
        }
    }

    @Test
    void tesLinkedList() {
        var head = new Solution.ListNode(1);
        var two = new Solution.ListNode(2);
        var three = new Solution.ListNode(3);
        var four = new Solution.ListNode(4);
        var five = new Solution.ListNode(5);
        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        var expected = new Solution.ListNode(1);
        var expectedTwo = new Solution.ListNode(2);
        var expectedThree = new Solution.ListNode(3);
        var expectedFour = new Solution.ListNode(4);
        var expectedFive = new Solution.ListNode(5);
        expected.next = expectedThree;
        expectedThree.next = expectedFive;
        expectedFive.next = expectedTwo;
        expectedTwo.next = expectedFour;

        var actual = SOLUTION.oddEvenList(head);

        while (actual != null && expected != null) {
            assertEquals(actual.val, expected.val);
            actual = actual.next;
            expected = expected.next;
        }
    }

    @Test
    void MyLinkedListTest() {
        MyLinkedList obj = new MyLinkedList();
        obj.addAtHead(1);
        obj.addAtTail(3);
        obj.addAtIndex(1, 2);
        obj.get(1);
        obj.deleteAtIndex(1);
        obj.get(1);
        obj.get(3);
        obj.deleteAtIndex(3);
        obj.deleteAtIndex(0);
        obj.get(0);
        obj.deleteAtIndex(0);
        obj.get(0);
    }
}
