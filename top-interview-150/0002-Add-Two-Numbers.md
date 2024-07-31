# Problem 2. Add Two Numbers

> [!NOTE]
> [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers/description/?envType=study-plan-v2&envId=top-interview-150)

You are given two **non-empty** linked lists representing two non-negative integers. The digits are stored in **reverse order**, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

### Examples

#### Example 1:

![0002-01.png](./images/0002-01.png)
> **Input**: `l1 = [2,4,3], l2 = [5,6,4]`<br/>
> **Output**: `[7,0,8]`<br/>
> **Explanation**: `342 + 465 = 807.`

#### Example 2:

> **Input**: `l1 = [0], l2 = [0]`<br/>
> **Output**: `[0]`

#### Example 3:

> **Input**: `l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]`<br/>
> **Output**: `[8,9,9,9,0,0,0,1]`

#### Constraints:

- The number of nodes in each linked list is in the range `[1, 100]`.
- `0 <= Node.val <= 9`
- It is guaranteed that the list represents a number that does not have leading zeros.

## Solutions

### Solution 1

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carryover = 0;
        ListNode result = null;
        while (l1 != null || l2 != null) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
            int units = (sum + carryover) % 10;
            carryover = (sum + carryover) / 10;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;

            if (result == null) {
                result = new ListNode(units);
            } else {
                add(result, units);
            }
        }

        if (carryover > 0) {
            add(result, carryover);
        }
        return result;
    }

    private void add(ListNode result, int val) {
        ListNode lastNode = result;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        lastNode.next = new ListNode(val);
    }
}
```

#### Complexities

- `Time Complexity`: O(max(m, n))
    - where `m` and `n` are the lengths of the input linked lists `l1` and `l2`.
    - This is because we iterate through both lists simultaneously until we reach the end of the longer list.
- `Space Complexity`: O(max(m, n))
    - This is because we create a new linked list to store the `result`, which can be at most the length of the longer input list.

### Solution 2

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carryover = 0;
        ListNode resultHead = new ListNode(0);
        ListNode tail = resultHead;
        while (l1 != null || l2 != null || carryover != 0) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
            int units = (sum + carryover) % 10;
            carryover = (sum + carryover) / 10;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            tail.next = new ListNode(units);
            tail = tail.next;
        }
        return resultHead.next;
    }
}
```

#### Complexities

- `Time Complexity`: O(max(m, n))
  - where `m` and `n` are the lengths of the input linked lists `l1` and `l2`.
  - This is because we iterate through both lists simultaneously until we reach the end of the longer list.
- `Space Complexity`: O(max(m, n))
  - This is because we create a new linked list to store the `result`, which can be at most the length of the longer input list.


> [!TIP]
> `Solution 1` and `Solution 2` have the same complexities, however `Solution 2` keeps a track of the tail of the list and updates (appends) it allowing to reduce the memory consumed.
