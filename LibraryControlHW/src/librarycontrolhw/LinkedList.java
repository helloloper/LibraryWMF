package datastructures;

// Linked list class to hold members' borrowed books
public class LinkedList<T> {

    // Node class as inner class
    private class LinkedListNode {
        T data;
        LinkedListNode next;

        // Node class' constructor
        public LinkedListNode(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private LinkedListNode head;
    private int size;

    // Constructor
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Method to add book to linked list
    public void addFirst(T data) {
        LinkedListNode newNode = new LinkedListNode(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Method to add book to linked list
    public void addLast(T data) {
        LinkedListNode newNode = new LinkedListNode(data);
        if (head == null) {
            head = newNode;
        } else {
            LinkedListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Method to remove a book from linked list
    public boolean remove(T data) {
        if (head == null) {
            return false;
        }
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }
        LinkedListNode current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Method to search linked list to see if it contains the book
    public boolean contains(T data) {
        LinkedListNode current = head;
        while (current != null) {
            if (current.data.equals(data)) return true;
            current = current.next;
        }
        return false;
    }

    // Method to see the size of linked list
    public int size() {
        return size;
    }

    // Method to print the linked list
    public void printList() {
        LinkedListNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}