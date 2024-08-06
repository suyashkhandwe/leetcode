# Design Linked List

> [!NOTE]
> [Design Linked List](https://leetcode.com/explore/learn/card/linked-list/209/singly-linked-list/1290/)

Design your implementation of the linked list. You can choose to use a singly or doubly linked list.

A node in a singly linked list should have two attributes: `val` and `next`. `val` is the value of the current node, and `next` is a pointer/reference to the next node.

If you want to use the doubly linked list, you will need one more attribute `prev` to indicate the previous node in the linked list. Assume all nodes in the linked list are **0-indexed**.

Implement the `MyLinkedList` class:

- `MyLinkedList()` Initializes the `MyLinkedList` object.
- `int get(int index)` Get the value of the `indexᵗʰ` node in the linked list. If the index is invalid, return `-1`. 
- `void addAtHead(int val)` Add a node of value `val` before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
- `void addAtTail(int val)` Append a node of value `val` as the last element of the linked list.
- `void addAtIndex(int index, int val)` Add a node of value `val` before the `indexᵗʰ` node in the linked list. If `index` equals the length of the linked list, the node will be appended to the end of the linked list. If `index` is greater than the length, the node **will not be inserted**.
- `void deleteAtIndex(int index)` Delete the `indexᵗʰ` node in the linked list, if the index is valid.

### Examples

#### Example 1:

> **Input**: `["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]`<br/>
> `[[], [1], [3], [1, 2], [1], [1], [1]]`<br/>
> **Output**: `[null, null, null, null, 2, null, 3]`<br/>
> **Explanation**: <br/>
> `MyLinkedList myLinkedList = new MyLinkedList();`<br/>
> `myLinkedList.addAtHead(1);`<br/>
> `myLinkedList.addAtTail(3);`<br/>
> `myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3`<br/>
> `myLinkedList.get(1);              // return 2`<br/>
> `myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3`<br/>
> `myLinkedList.get(1);              // return 3`<br/>

#### Constraints:

- `0 <= index, val <= 1000`
- Please do not use the built-in LinkedList library.
- At most `2000` calls will be made to `get`, `addAtHead`, `addAtTail`, `addAtIndex` and `deleteAtIndex`.

## Solutions

### Solution 1

```java
public class MyLinkedList {

    private static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    int size;
    Node head;

    public MyLinkedList() {
        size = 0;
    }

    public int get(int index) {
        if (index >= size) {
            return -1;
        } else {
            Node iterator = head;
            for (int i = 0; i < index; i++) {
                iterator = iterator.next;
            }
            return iterator.val;
        }
    }

    public void addAtHead(int val) {
        head = new Node(val, head);
        size++;
    }

    public void addAtTail(int val) {
        if (head == null) {
            addAtHead(val);
        } else {
            Node iterator = head;
            while (iterator.next != null) {
                iterator = iterator.next;
            }
            iterator.next = new Node(val);
            size++;
        }
    }

    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else if (index < size) {
            Node node = new Node(val);
            Node iterator = head;
            for (int i = 0; i < index - 1; i++) {
                iterator = iterator.next;
            }
            node.next = iterator.next;
            iterator.next = node;
            size++;
        }
    }

    public void deleteAtIndex(int index) {
        if (index == 0) {
            head = head.next;
            size--;
        } else if (index < size) {
            Node iterator = head;
            for (int i = 0; i < index - 1; i++) {
                iterator = iterator.next;
            }
            if (iterator.next != null) {
                iterator.next = iterator.next.next;
                size--;
            }
        }
    }
}
```
