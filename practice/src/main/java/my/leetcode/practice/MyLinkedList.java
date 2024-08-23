package my.leetcode.practice;

public class MyLinkedList {

    private static class Node {
        int val;
        Node next;
        Node prev;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
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
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.val;
        }
    }

    public void addAtHead(int val) {
        head = new Node(val, head, null);
        if (head.next != null) {
            head.next.prev = head;
        }
        size++;
    }

    public void addAtTail(int val) {
        if (head == null) {
            addAtHead(val);
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(val, null, current);
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
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            node.next = current.next;
            current.next = node;
            size++;
        }
    }

    public void deleteAtIndex(int index) {
        if (index == 0) {
            head = head.next;
            size--;
        } else if (index < size) {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            size--;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */