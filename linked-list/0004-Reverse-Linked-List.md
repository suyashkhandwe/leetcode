# Reverse Linked List

> [!NOTE]
> [Reverse Linked List](https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1205/)

Given the `head` of a singly linked list, reverse the list, and return the reversed list.


### Examples

#### Example 1:

![0004-001.png](./images/0004-001.png)
> **Input**: `head = [1,2,3,4,5]`<br/>
> **Output**: `[5,4,3,2,1]`

#### Example 2:

![0004-002.png](./images/0004-002.png)
> **Input**: `head = [1,2]`<br/>
> **Output**: `[2,1]`

#### Example 3:

> **Input**: `head = []`<br/>
> **Output**: `[]`

#### Constraints:

- The number of nodes in the list is the range `[0, 5000]`.
- `-5000 <= Node.val <= 5000`

## Solutions

### Solution 1

```java
public ListNode reverseList(ListNode head) {
    ListNode current = head;
    ListNode previous = null;
    while(current != null) {
        ListNode next = current.next;
        current.next = previous;
        previous = current;
        current = next;
    }
    return previous;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - This is because we need to iterate through each node in the linked list once to reverse it.
- `Space Complexity`: O(1)
    - This is because we are using a constant amount of extra space regardless of the size of the input linked list.

### Solution 2

```java
public ListNode reverseList(ListNode head) {
        return reverseList(head, null);
    }

public ListNode reverseList(ListNode head, ListNode previous) {
    if (head == null) {
        return previous;
    }
    ListNode next = head.next;
    head.next = previous;
    return reverseList(next, head);
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the number of nodes in the linked list.
    - This is because the function visits each node exactly once during the recursion.
- `Space Complexity`: O(n)
    - This is because the recursive calls are stored on the call stack, and in the worst case scenario, the call stack can grow to a depth of n, where n is the number of nodes in the linked list.
