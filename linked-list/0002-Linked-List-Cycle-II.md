# Linked List Cycle II

> [!NOTE]
> [Linked List Cycle II](https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1214/)

Given the `head` of a linked list, return the node where the cycle begins. If there is no cycle, return `null`.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the `next` pointer. Internally, `pos` is used to denote the index of the node that tail's `next` pointer is connected to (**0-indexed**). It is `-1` if there is no cycle. **Note that `pos` is not passed as a parameter**.

**Do not modify** the linked list.

### Examples

#### Example 1:

![0002-001.png](./images/0002-001.png)
> **Input**: `head = [3,2,0,-4], pos = 1`<br/>
> **Output**: `tail connects to node index 1`<br/>
> **Explanation**: `There is a cycle in the linked list, where tail connects to the second node.`

#### Example 2:

![0002-002.png](./images/0002-002.png)
> **Input**: `head = [1,2], pos = 0`<br/>
> **Output**: `tail connects to node index 0`<br/>
> **Explanation**: `There is a cycle in the linked list, where tail connects to the first node.`

#### Example 3:

![0002-003.png](./images/0002-003.png)
> **Input**: `head = [1], pos = -1`<br/>
> **Output**: `no cycle`<br/>
> **Explanation**: `There is no cycle in the linked list.`

#### Constraints:

- The number of the nodes in the list is in the range `[0, 10⁴]`.
- `-10⁵ <= Node.val <= 10⁵`
- `pos` is `-1` or a **valid index** in the linked-list.

**Follow up**: Can you solve it using `O(1)` (i.e. constant) memory?

## Solutions

### Solution 1

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
              // We found a cycle. Now, we start iterating with 2 pointers, one starting at head and the other starting at slow.
              // We continue iterating till slow and iterator reach the same node and return this node which indicates where the cycle starts. 
              ListNode iterator = head;
                while (slow != iterator) {
                    slow = slow.next;
                    iterator = iterator.next;
                }
                return slow;
            }
        }
        return null;
    }
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the number of nodes in the linked list.
    - This is because in the worst case scenario, the `fast` pointer will traverse the entire linked list once before detecting the cycle.
- `Space Complexity`: O(1)
    - This is because the solution uses only a constant amount of extra space regardless of the size of the input linked list.
