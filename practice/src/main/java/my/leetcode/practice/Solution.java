package my.leetcode.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class Solution {

    public Node copyRandomList(Node head) {

        Node iterator = head;
        Node nextCopy;
        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (iterator != null) {
            nextCopy = iterator.next;
            iterator.next = new Node(iterator.val);
            iterator.next.next = nextCopy;
            iterator = nextCopy;
        }

        iterator = head;
        // Second round: assign random pointers for the copy nodes.
        while (iterator != null) {
            if (iterator.random != null) {
                iterator.next.random = iterator.random.next;
            }
            iterator = iterator.next.next;
        }


        // Third round: restore the original list, and extract the copy list.
        iterator = head;
        Node copyDummyHead = new Node(0);
        Node copyIterator = copyDummyHead;
        Node copy;
        while (iterator != null) {
            nextCopy = iterator.next.next;
            copy = iterator.next;

            copyIterator.next = copy;
            copyIterator = copy;

            iterator.next = nextCopy;
            iterator = nextCopy;

        }
        return copyDummyHead.next;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
