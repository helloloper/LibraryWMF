package librarycontrolhw;

public class BookLinkedList<T> {
    Node head = null;

    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {

            this.head = newNode;
            newNode = head;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.prev = temp;

        }
    }

    public void remove(T data) {
        if (head == null) {
            System.out.println("[!] The list is empty.");
        } else {
            Node temp = head;
            while (!temp.data.equals(data) && temp.data != null) {// |x| |x| |d|
                temp = temp.next;
            }
            if (temp == null) {
                System.out.println("[!] Book not found in the list.");
            } else if (temp == head) {
                temp.prev = null;
                head = temp.next;
                System.out.println("[✓] Item removed.");

            } else if (temp.next == null) {
                temp.prev.next = null;
                System.out.println("[✓] Item removed.");

            } else {

                temp.next.prev = temp.prev;
                temp.prev.next = temp.next;
                System.out.println("[✓] Item removed.");

            }
        }
    }

    public void seeList() {
        if (head == null) {
            System.out.println("[i] No items in the list.");
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                System.out.println(temp.data);
                temp = temp.next;
            }
            System.out.println(temp.data);
        }
    }

    public Book getBook(int bookID) {
        if (head == null) {
            System.out.println("[i] You have not borrowed any books.");
            return null;
        } else {
            Node<Book> temp = head;
            while (temp.data.getId() != bookID && temp.next != null) {
                temp = temp.next;
            }
            if (temp.data.getId() == bookID) {
                return temp.data;
            } else {
                return null;
            }
        }

    }

}
