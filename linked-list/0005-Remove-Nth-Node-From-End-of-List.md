# Remove Nth Node From End of List

> [!NOTE]
> [Remove Nth Node From End of List](https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1296/)

Given the `head` of a linked list, remove the `nth` node from the end of the list and return its head.

### Examples

#### Example 1:

![0005-001.png](./images/0005-001.png)
> **Input**: `head = [1,2,3,4,5], n = 2`<br/>
> **Output**: `[1,2,3,5]`

#### Example 2:

> **Input**: `head = [1], n = 1`<br/>
> **Output**: `[]`

#### Example 3:

> **Input**: `head = [1,2], n = 1`<br/>
> **Output**: `[1]`

#### Constraints:

- The number of nodes in the list is `sz`.
- `1 <= sz <= 30`
- `0 <= Node.val <= 100`
- `1 <= n <= sz`

**Follow up**: Could you do this in one pass?

## Solutions

### Solution 1

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode current = head;
    int counter = 0;
    while (current != null) {
        counter++;
        current = current.next;
    }
    ListNode dummyHead = new ListNode(-1);
    ListNode dummyCurrent = dummyHead;
    current = head;
    while (counter - n > 0) {
        dummyCurrent.next = current;
        current = current.next;
        dummyCurrent = dummyCurrent.next;
        counter--;
    }
    dummyCurrent.next = current.next;
    return dummyHead.next;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the number of nodes in the linked list.
    - This is because we iterate through the linked list twice - once to count the number of nodes and once to remove the nth node from the end.
- `Space Complexity`: O(1)
    - This is because we are using a constant amount of extra space regardless of the size of the input linked list.

### Solution 2

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode fast = head;
    ListNode slow = head;
    // Move the fast pointer `n` steps ahead.
    for (int i = 0; i < n; i++) {
        fast = fast.next;
    }
    // If we have reach the end of the list, return the next node from the head.
    if (fast == null) {
        return head.next;
    }
    
    // Iterate while fast.next is not at the end and move both `fast` and `slow` pointers.
    while (fast.next != null) {
        fast = fast.next;
        slow = slow.next;
    }
    // At this stage, fast has reached to the end, and slow is lagging by `n` nodes.
    // Set the slow.next to the next node of slow.next.
    slow.next = slow.next.next;
    return head;
    }
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the number of nodes in the linked list.
    - This is because we iterate through the linked list once with 2 pointers.
- `Space Complexity`: O(1)
    - This is because we are using a constant amount of extra space regardless of the size of the input linked list.

> [!TIP]
> `Solution 2` completes the removal in one iteration.
