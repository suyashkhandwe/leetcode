# Problem 138. Copy List with Random Pointer

> [!NOTE]
> [138. Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/description/?envType=study-plan-v2&envId=top-interview-150)

A linked list of length `n` is given such that each node contains an additional random pointer, which could point to any node in the list, or `null`.

Construct a [deep copy](https://en.wikipedia.org/wiki/Object_copying#Deep_copy) of the list. The deep copy should consist of exactly `n` **brand new** nodes, where each new node has its value set to the value of its corresponding original node. Both the `next` and `random` pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. **None of the pointers in the new list should point to nodes in the original list**.

For example, if there are two nodes `X` and `Y` in the original list, where `X.random --> Y`, then for the corresponding two nodes `x` and `y` in the copied list, `x.random --> y`.

Return _the head of the copied linked list_.

The linked list is represented in the input/output as a list of `n` nodes. Each node is represented as a pair of `[val, random_index]` where:
- `val`: an integer representing `Node.val`
- `random_index`: the index of the node (range from `0` to `n-1`) that the `random` pointer points to, or `null` if it does not point to any node.

Your code will **only** be given the `head` of the original linked list.

### Examples

#### Example 1:

![0138-01.png](./images/0138-01.png)
> **Input**: `head = [[7,null],[13,0],[11,4],[10,2],[1,0]]`<br/>
> **Output**: `[[7,null],[13,0],[11,4],[10,2],[1,0]]`

#### Example 2:

![0138-02.png](./images/0138-02.png)
> **Input**: `head = [[1,1],[2,1]]`<br/>
> **Output**: `[[1,1],[2,1]]`

#### Example 3:

![0138-03.png](./images/0138-03.png)
> **Input**: `head = [[3,null],[3,0],[3,null]]`<br/>
> **Output**: `[[3,null],[3,0],[3,null]]`

#### Constraints:

- `0 <= n <= 1000`
- `-10⁴ <= Node.val <= 10⁴`
- `Node.random` is `null` or is pointing to some node in the linked list.

## Solutions

### Solution 1

```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
  public Node copyRandomList(Node head) {

    Node iterator = head;
    Node nextCopy;
    // First round: make copy of each node,
    // and link them together side-by-side in a single list.
    while (iterator != null) {
      nextCopy = iterator.next;
      iterator.next = new Node(iterator.val);
      iterator.next.next = nextCopy;
      iterator = nextCopy;
    }

    iterator = head;
    // Second round: assign random pointers for the copy nodes.
    while (iterator != null) {
      if (iterator.random != null) {
        iterator.next.random = iterator.random.next;
      }
      iterator = iterator.next.next;
    }


    // Third round: restore the original list, and extract the copy list.
    iterator = head;
    Node copyDummyHead = new Node(0);
    Node copyIterator = copyDummyHead;
    Node copy;
    while (iterator != null) {
      nextCopy = iterator.next.next;
      copy = iterator.next;

      copyIterator.next = copy;
      copyIterator = copy;

      iterator.next = nextCopy;
      iterator = nextCopy;

    }
    return copyDummyHead.next;
  }
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the number of nodes in the original linked list.
    - This is because we iterate through the original linked list three times, each time visiting each node once.
- `Space Complexity`: O(n)
    - This is because we are creating a new copy of each node in the original linked list, resulting in a new linked list with the same number of nodes.
    - The auxiliary space complexity is O(1).

> [!TIP]
> Refer [this solution](https://leetcode.com/problems/copy-list-with-random-pointer/solutions/43491/a-solution-with-constant-space-complexity-o-1-and-linear-time-complexity-o-n/) for details.
