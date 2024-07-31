# Problem 141. Linked List Cycle

> [!NOTE]
> [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/description/?envType=study-plan-v2&envId=top-interview-150)

Given `head`, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the `next` pointer. Internally, `pos` is used to denote the index of the node that tail's next pointer is connected to. **Note that `pos` is not passed as a parameter**.

Return `true` _if there is a cycle in the linked list_. Otherwise, return `false`.

### Examples

#### Example 1:

![0141-01.png](./images/0141-01.png)
> **Input**: `head = [3,2,0,-4], pos = 1`<br/>
> **Output**: `true`<br/>
> **Explanation**: `There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).`

#### Example 2:

![0141-02.png](./images/0141-02.png)
> **Input**: `head = [1,2], pos = 0`<br/>
> **Output**: `true`<br/>
> **Explanation**: `There is a cycle in the linked list, where the tail connects to the 0th node.`

#### Example 3:

![0141-03.png](./images/0141-03.png)
> **Input**: `head = [1], pos = -1`<br/>
> **Output**: `false`<br/>
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
public boolean hasCycle(ListNode head) {
    if (head == null) {
        return false;
    }
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
            return true;
        }
    }
    return false;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the number of nodes in the linked list.
    - This is because in the worst case scenario, we may need to iterate through all the nodes in the linked list to determine if there is a cycle.
- `Space Complexity`: O(1)
    - This is because we are using only a constant amount of extra space regardless of the size of the input linked list.
    - We are only using two pointers (slow and fast) to traverse the linked list.

> [!TIP]
> This solution uses the [Floyd’s Cycle Finding Algorithm](https://www.geeksforgeeks.org/floyds-cycle-finding-algorithm/) or `Hare-Tortoise algorithm` similar to [0202 - Happy Number](./0202-Happy-Number.md).
