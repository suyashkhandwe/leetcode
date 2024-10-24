# Problem N. XXX

> [!NOTE]
> [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/?envType=study-plan-v2&envId=top-interview-150)

Given the `head` of a linked list, remove the `nth` node from the end of the list and return its head.

### Examples

#### Example 1:

![0019-01.png](./images/0019-01.png)
> **Input**: `head = [1,2,3,4,5], n = 2`<br/>
> **Output**: `[1,2,3,5]`

#### Example 2:

> **Input**: `head = [1], n = 1`<br/>
> **Output**: `[]`

#### Example 3:

> **Input**: `head = [1,2], n = 1`<br/>
> **Output**: `[]`

#### Constraints:

- The number of nodes in the list is `sz`. 
- `1 <= sz <= 30`
- `0 <= Node.val <= 100`
- `1 <= n <= sz`

## Solutions

### Solution 1

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    var fast = head;
    var slow = head;
    for (int i = 0; i < n; i++) {
        fast = fast.next;
    }
    if (fast == null) {
        return head.next;
    }
    
    while (fast.next != null) {
        fast = fast.next;
        slow = slow.next;
    }
    slow.next = slow.next.next;
    return head;
}
```

#### Complexities

- `Time Complexity`: ?
    - ?
- `Space Complexity`: ?
    - ?

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