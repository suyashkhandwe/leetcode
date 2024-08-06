package my.leetcode.practice;

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

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */