# Reverse Linked List

## Solutions

### Solution 1

```java
public ListNode reverseList(ListNode head) {
    ListNode current = head;
    ListNode previous = null;
    ListNode next = null;
    while(current != null) {
        next = current.next;
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

> [!TIP]
> ?