package librarycontrolhw;

public class Stack<T> {

    private Node<T> top;
    private int size;

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void printStack() {
        if (isEmpty()) {
            System.out.println("[i] Stack is empty.");
            return;
        }
        Node<T> current = top;
        System.out.println("--- Undo Stack (LIFO) ---");
        while (current != null) {
            System.out.println(" <- " + current.data);
            current = current.next;
        }
        System.out.println("------------");
    }
}
