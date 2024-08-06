# Get At Index

Get At Index sample implementation

## Solutions

### Solution 1

```java
public int getAtIndex(ListNode head, int index) {
    int currIdx = 0;
    ListNode current = head;
    while (current != null) {
        if (index == currIdx) {
            return current.val;
        }
        current = current.next;
        currIdx++;
    }
    return Integer.MIN_VALUE;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the number of nodes in the linked list.
    - This is because we iterate through each node once.
- `Space Complexity`: O(1)
    - This is because we use a constant space.

### Solution 2

```java
public int getAtIndex(ListNode head, int index) {
    if (head == null) {
        return Integer.MIN_VALUE;
    } if (index == 0) {
        return head.val;
    }
    return getAtIndex(head.next, index - 1);
}
```

#### Complexities

- `Time Complexity`: O(n)
  - where `n` is the number of nodes in the linked list.
  - This is because we iterate through each node once.
- `Space Complexity`: O(n)
  - This is because although we use a constant space, recursion causes extra storage on the stack - one for each recursive call.


> [!TIP]
> `Solution 1` is iterative while `Solution 2` is recursive and these both demonstrate the 2 ways to traverse Linked Lists. 
