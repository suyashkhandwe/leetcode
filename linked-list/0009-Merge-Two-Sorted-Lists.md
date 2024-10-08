# Merge Two Sorted Lists

> [!NOTE]
> [Merge Two Sorted Lists](https://leetcode.com/explore/learn/card/linked-list/213/conclusion/1227/)

You are given the heads of two sorted linked lists `list1` and `list2`.

Merge the two lists into one **sorted** list. The list should be made by splicing together the nodes of the first two lists.

Return _the head of the merged linked list_.

### Examples

#### Example 1:

![0009-001.png](./images/0009-001.png)
> **Input**: `list1 = [1,2,4], list2 = [1,3,4]`<br/>
> **Output**: `[1,1,2,3,4,4]`

#### Example 2:

> **Input**: `list1 = [], list2 = []`<br/>
> **Output**: `[]`

#### Example 3:

> **Input**: `list1 = [], list2 = [0]`<br/>
> **Output**: `[0]`

#### Constraints:

- The number of nodes in both lists is in the range `[0, 50]`.
- `-100 <= Node.val <= 100`
- Both `list1` and `list2` are sorted in **non-decreasing** order.

## Solutions

### Solution 1

```java
public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    ListNode merged = new ListNode(Integer.MIN_VALUE);
    ListNode current = merged;
    while (list1 != null && list2 != null) {
        if (list1.val < list2.val) {
            current.next = list1;
            list1 = list1.next;
        } else {
            current.next = list2;
            list2 = list2.next;
        }
        current = current.next;
    }
    if (list1 == null) {
        current.next = list2;
    } 
    if (list2 == null) {
        current.next = list1;
    }
    return merged.next;
}
```

#### Complexities

- `Time Complexity`: O(n + m)
    - where `n` and `m` are the lengths of the two input lists.
    - This is because we iterate through both lists once to merge them together.
- `Space Complexity`: O(1)
    - This is because we are not using any extra space that grows with the input size.
    - We are simply modifying the pointers of the existing nodes to create the merged list.
