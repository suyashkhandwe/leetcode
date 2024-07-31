package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carryover = 0;
        ListNode resultHead = new ListNode(0);
        ListNode tail = resultHead;
        while (l1 != null || l2 != null || carryover != 0) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carryover;
            int units = sum % 10;
            carryover = sum / 10;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            tail.next = new ListNode(units);
            tail = tail.next;
        }
        return resultHead.next;
    }

    public class ListNode {
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
