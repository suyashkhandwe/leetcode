# Palindrome Linked List

> [!NOTE]
> [Palindrome Linked List](https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1209/)

Given the `head` of a singly linked list, return `true` if it is a palindrome or `false` otherwise.

### Examples

#### Example 1:

![0008-001.png](./images/0008-001.png)
> **Input**: `head = [1,2,2,1]`<br/>
> **Output**: `true`

#### Example 2:

![0008-002.png](./images/0008-002.png)
> **Input**: `head = [1,2]`<br/>
> **Output**: `false`

#### Constraints:

- The number of nodes in the list is in the range `[1, 10⁵]`.
- `0 <= Node.val <= 9`

**Follow up**: Could you do it in `O(n)` time and `O(1)` space?

## Solutions

### Solution 1

```java
public boolean isPalindrome(ListNode head) {
    ListNode current = head;
    int count = 0;
    while (current != null) {
        count++;
        current = current.next;
    }
    int[] nodes = new int[count];
    int counter = 0;
    current = head;
    while (current != null) {
        nodes[counter++] = current.val;
        current = current.next;
    }
    for (int i = 0, j = count - 1; i < j; i++, j--) {
        if (nodes[i] != nodes[j]) {
            return false;
        }
    }
    return true;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the number of nodes in the linked list.
    - This is because we iterate through the linked list twice - once to count the number of nodes and once to store the values of the nodes in an array.
- `Space Complexity`: O(n)
    - This is because we are storing the values of all the nodes in an array of size `n`.

### Solution 2

```java
public boolean isPalindrome(ListNode head) {
    // Move the slow and fast pointers such that fast pointer reaches to the end which would make the slow pointer reach to the mid-point.
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    // Ensure that the slow pointer is at the mid-point and doesn't stop before.
    // Revers from slow pointer i.e. from mid-point.
    slow = reverse((fast == null) ? slow : slow.next);
    
    // Now compare from `head` and the `slow` pointer and if they don't match, return false. 
    while (slow != null && head != null) {
        if (head.val != slow.val) {
            return false;
        }
        slow = slow.next;
        head = head.next;
    }
    return true;
}

private ListNode reverse(ListNode head) {
    ListNode current = head;
    ListNode previous = null;
    while (current != null) {
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
    - where `n` is the number of nodes in the linked list.
    - This is because we iterate through the linked list twice - once to find the middle node and once to reverse the second half of the list.
- `Space Complexity`: O(1)
    - This is because we are using a constant amount of extra space regardless of the size of the input linked list.

> [!TIP]
> `Solution 2` modifies the linked list from mid-point.<br/>
> Though typically modifying the input linked list is not a general practice, this is the only way to get `O(1)` space complexity.
