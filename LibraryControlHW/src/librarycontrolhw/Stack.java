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
        if(isEmpty()) {
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
        if(isEmpty()) {
            System.out.println("no action");
            return;
        }
        Node<T> current = top;
        System.out.println("UNDO Actions (LIFO) : " );
        while (current != null) {
            System.out.println(" <- " + current.data);
            current = current.next;
        }
        System.out.println("------------");
    }
}
