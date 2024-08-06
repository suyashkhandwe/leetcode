# Reverse Linked List

## Solutions

### Solution 1

```java
public ListNode reverseList(ListNode head) {
    if (head == null) {
        return null;
    }
    ListNode current = head;
    ListNode previous = null;
    ListNode next;
    next = current.next;
    current.next = previous;
    previous = current;
    current = next;

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

```

#### Complexities

- `Time Complexity`: ?
    - ?
- `Space Complexity`: ?
    - ?

> [!TIP]
> ?