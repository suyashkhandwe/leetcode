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
    void testGetAtIndex() {
        var head = new Solution.ListNode(1);
        var two = new Solution.ListNode(2);
        var three = new Solution.ListNode(3);
        var four = new Solution.ListNode(4);
        var five = new Solution.ListNode(5);
        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        var eReversed = new Solution.ListNode(5);
        var eReversedFour = new Solution.ListNode(4);
        var eReversedThree = new Solution.ListNode(3);
        var eReversedTwo = new Solution.ListNode(2);
        var eReversedOne = new Solution.ListNode(1);
        eReversed.next = eReversedFour;
        eReversedFour.next = eReversedThree;
        eReversedThree.next = eReversedTwo;
        eReversedTwo.next = eReversedOne;

        var aReversed = SOLUTION.reverseList(head);

        while (aReversed != null && eReversed != null) {
            assertEquals(eReversed.val, aReversed.val);
            eReversed = eReversed.next;
            aReversed = aReversed.next;
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
