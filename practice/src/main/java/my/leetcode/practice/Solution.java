package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = reverse((fast == null) ? slow : slow.next);
        while (slow != null && head != null) {
            if (head.val != slow.val) {
                return false;
            }
            slow = slow.next;
            head = head.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode current = head;
        ListNode previous = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode headAIterator = headA;
        ListNode headBIterator = headB;
        while (headAIterator != headBIterator) {
            headAIterator = headAIterator != null ? headAIterator.next : headA;
            headBIterator = headBIterator != null ? headBIterator.next : headB;
        }
        return headAIterator;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        int totalNodes = 0;
        ListNode iterator = head;
        while (iterator != null) {
            totalNodes++;
            iterator = iterator.next;
        }

        for (int i = 0; i + k <= totalNodes; i = i + k) {
            head = reverseBetween(head, i + 1, i + k);
        }
        return head;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode reversedCopy = new ListNode(0);
        reversedCopy.next = head;
        ListNode prevNode = reversedCopy;
        ListNode currNode = prevNode.next;

        int i = 1;
        // Iterate only till right
        while (i++ < right) {
            // If we passed left, start reversing
            if (i > left) {
                // Set the next node to the node after the known current node
                ListNode nextNode = currNode.next;
                currNode.next = nextNode.next;
                nextNode.next = prevNode.next;
                prevNode.next = nextNode;
            } else {
                // Move previous node to the next node of previous node while we haven't reached left
                prevNode = prevNode.next;
                // Set the current node to the node after the known previous node
                currNode = prevNode.next;
            }
        }
        return reversedCopy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
