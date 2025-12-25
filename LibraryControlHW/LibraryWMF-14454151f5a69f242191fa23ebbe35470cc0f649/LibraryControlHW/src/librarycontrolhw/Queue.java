package librarycontrolhw;

public class Queue<T> {

    private class QueueNode {
        T data;
        QueueNode next;

        public QueueNode(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private QueueNode front;
    private QueueNode rear;
    private int size;

    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(T data) {
        QueueNode newNode = new QueueNode(data);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (front == null) {
            return null;
        }
        T removedData = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return removedData;
    }

    public T peek() {
        if (front == null) {
            return null;
        }
        return front.data;
    }

    public void printQueue() {
        QueueNode current = front;
        if (current == null) {
            System.out.println("  [i] Waitlist is empty.");
            return;
        }
        System.out.print("  Waitlist: [Front] ");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println(" [End]");
    }

}
